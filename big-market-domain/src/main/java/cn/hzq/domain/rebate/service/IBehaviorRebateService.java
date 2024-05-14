package cn.hzq.domain.rebate.service;

import cn.hzq.domain.rebate.model.entity.BehaviorEntity;
import cn.hzq.domain.rebate.model.entity.BehaviorRebateOrderEntity;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/10
 * @Description 行为返利接口服务
 **/
public interface IBehaviorRebateService {
    /**
     * 创建行文返利动作的入账订单
     *
     * @param behaviorEntity 行为实体对象
     * @return 订单Id
     */
    List<String> createOrder(BehaviorEntity behaviorEntity);

    /**
     * 通过用户Id和外部透传业务Id查询已经产生的订单
     * @param userId 用户Id
     * @param outBusinessNo 外部透传Id
     * @return 订单实体对象
     */
    List<BehaviorRebateOrderEntity> queryOrderByOutBusinessNo(String userId, String outBusinessNo);
}
