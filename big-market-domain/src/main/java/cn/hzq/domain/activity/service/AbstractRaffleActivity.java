package cn.hzq.domain.activity.service;

import cn.hzq.domain.activity.model.entity.*;
import cn.hzq.domain.activity.repository.IActivityRepository;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 黄照权
 * @Date 2024/4/24
 * @Description 抽奖活动抽象类，定义标准的流程
 **/
@Slf4j
public class AbstractRaffleActivity implements IRaffleOrder {
    protected IActivityRepository activityRepository;
    /**
     * 构造器方式注入
     *
     * @param activityRepository 仓储服务对象
     */
    public AbstractRaffleActivity(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public ActivityOrderEntity createRaffleActivityOrder(ActivityShopCartEntity activityShopCartEntity) {
        // 1、通过活动sku查询活动信息
        ActivitySkuEntity activitySkuEntity = activityRepository.queryActivitySkuBySku(activityShopCartEntity.getSku());
        // 2、查询活动信息
        ActivityEntity activityEntity = activityRepository.queryRaffleActivityByActivityId(activitySkuEntity.getActivityId());
        // 3、查询次数信息
        ActivityCountEntity activityCountEntity = activityRepository.queryRaffleActivityCountByActivityCountId(activitySkuEntity.getActivityCountId());
        log.info("查询结果：{} {} {}", JSON.toJSONString(activitySkuEntity), JSON.toJSONString(activityEntity), JSON.toJSONString(activityCountEntity));
        // 4、返回活动订单对象
        return ActivityOrderEntity.builder().build();
    }
}
