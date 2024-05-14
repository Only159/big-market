package cn.hzq.domain.rebate.repository;

import cn.hzq.domain.rebate.model.aggregate.BehaviorRebateAggregate;
import cn.hzq.domain.rebate.model.entity.BehaviorRebateOrderEntity;
import cn.hzq.domain.rebate.model.valobj.BehaviorTypeVO;
import cn.hzq.domain.rebate.model.valobj.DailyBehaviorRebateVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/10
 * @Description 行为返利服务仓促接口
 **/
public interface IBehaviorRebateRepository {
    /**
     * 保存用户行为返利流水订单
     *
     * @param userId                   用户Id
     * @param behaviorRebateAggregates 行为返利聚合对象
     */
    void saveUserRebateRecord(String userId, ArrayList<BehaviorRebateAggregate> behaviorRebateAggregates);

    /**
     * 根据行为枚举对象 查找返利行为配置信息
     *
     * @param behaviorTypeVO 行为枚举对象
     * @return 行为配置信息
     */
    List<DailyBehaviorRebateVO> queryDailyBehaviorRebateConfig(BehaviorTypeVO behaviorTypeVO);

    /**
     * 通过用户Id和外部透传业务Id查询已经产生的订单
     * @param userId 用户Id
     * @param outBusinessNo 外部透传Id
     * @return 订单实体对象
     */
    List<BehaviorRebateOrderEntity> queryOrderByOutBusinessNo(String userId, String outBusinessNo);
}
