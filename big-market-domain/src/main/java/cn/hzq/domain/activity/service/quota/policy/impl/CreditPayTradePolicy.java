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
 * @Description: 积分支付策略
 */
@Component("credit_pay_trade")
public class CreditPayTradePolicy implements ITradePolicy {
    private final IActivityRepository activityRepository;

    public CreditPayTradePolicy(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void trade(CreateQuotaOrderAggregate createQuotaOrderAggregate) {
        //订单状态为待支付
        createQuotaOrderAggregate.setOrderState(OrderStateVO.wait_pay);
        //保存订单
        activityRepository.doSaveCreditPayOrder(createQuotaOrderAggregate);
    }
}
