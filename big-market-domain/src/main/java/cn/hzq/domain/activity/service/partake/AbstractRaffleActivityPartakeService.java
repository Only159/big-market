package cn.hzq.domain.activity.service.partake;

import cn.hzq.domain.activity.model.aggregate.CreatePartakeOrderAggregate;
import cn.hzq.domain.activity.model.entity.ActivityEntity;
import cn.hzq.domain.activity.model.entity.PartakeRaffleActivityEntity;
import cn.hzq.domain.activity.model.entity.UserRaffleOrderEntity;
import cn.hzq.domain.activity.model.valobj.ActivityStateVO;
import cn.hzq.domain.activity.repository.IActivityRepository;
import cn.hzq.domain.activity.service.IRaffleActivityPartakeService;
import cn.hzq.types.enums.ResponseCode;
import cn.hzq.types.exception.AppException;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 抽奖活动参与抽象类
 **/
@Slf4j
public abstract class AbstractRaffleActivityPartakeService implements IRaffleActivityPartakeService {
    protected final IActivityRepository activityRepository;

    public AbstractRaffleActivityPartakeService(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public UserRaffleOrderEntity createOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity) {
        // 0.基础信息
        String userId = partakeRaffleActivityEntity.getUserId();
        Long activityId = partakeRaffleActivityEntity.getActivityId();
        Date currentDate = new Date();

        // 1.活动查询
        ActivityEntity activityEntity = activityRepository.queryRaffleActivityByActivityId(activityId);
        log.info("活动【有效期、状态】校验开始。 activityId:{}", activityEntity.getActivityId());
        // 校验：活动状态
        if (!ActivityStateVO.open.equals(activityEntity.getState())) {
            throw new AppException(ResponseCode.ACTIVITY_STATE_ERROR.getCode(), ResponseCode.ACTIVITY_STATE_ERROR.getInfo());
        }
        // 校验：活动日期【开始时间<-当前时间 ->结束时间】
        if (activityEntity.getBeginDateTime().after(currentDate) || activityEntity.getEndDateTime().before(currentDate)) {
            throw new AppException(ResponseCode.ACTIVITY_DATE_ERROR.getCode(), ResponseCode.ACTIVITY_DATE_ERROR.getInfo());
        }

        // 2.查询未被使用的活动参与订单
        UserRaffleOrderEntity userRaffleOrderEntity = activityRepository.queryNoUserRaffleOrder(partakeRaffleActivityEntity);
        if (null != userRaffleOrderEntity) {
            log.info("创建抽奖活动订单【已存在未消费】userId:{} activityId:{} userRaffleOrderEntity:{}", userId, activityId, JSON.toJSONString(userRaffleOrderEntity));
            return userRaffleOrderEntity;
        }
        // 3.账户额度过滤&返回账户构建对象
        CreatePartakeOrderAggregate createPartakeOrderAggregate = this.doFilterAccount(userId, activityId, currentDate);
        // 4.构建订单
        UserRaffleOrderEntity userRaffleOrder = this.buildUserRaffleOrder(userId, activityId, currentDate);
        // 5.填充实体对象
        createPartakeOrderAggregate.setUserRaffleOrderEntity(userRaffleOrder);
        // 6.保存聚合对象
        activityRepository.saveCreatePartakeOrderAggregate(createPartakeOrderAggregate);
        // 7.返回订单信息
        return userRaffleOrder;
    }

    protected abstract CreatePartakeOrderAggregate doFilterAccount(String userId, Long activityId, Date currentDate);

    protected abstract UserRaffleOrderEntity buildUserRaffleOrder(String userId, Long activityId, Date currentDate);
}
