package cn.hzq.domain.activity.service.quota.policy.impl;

import cn.hzq.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import cn.hzq.domain.activity.model.valobj.OrderStateVO;
import cn.hzq.domain.activity.repository.IActivityRepository;
import cn.hzq.domain.activity.service.quota.policy.ITradePolicy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-15
 * @Description: 无需支付返利策略
 */
@Component("rebate_no_pay_trade")
public class RebateNoPayTradePolicy implements ITradePolicy {
    private final IActivityRepository activityRepository;

    public RebateNoPayTradePolicy(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void trade(CreateQuotaOrderAggregate createQuotaOrderAggregate) {
        //订单状态为完成
        createQuotaOrderAggregate.setOrderState(OrderStateVO.completed);
        //订单无需支付，设置为0
        createQuotaOrderAggregate.getActivityOrderEntity().setPayAmount(BigDecimal.ZERO);
        //保存订单
        activityRepository.doSaveNoPayOrder(createQuotaOrderAggregate);
    }
}
