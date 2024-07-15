package cn.hzq.domain.activity.service.quota.policy;

import cn.hzq.domain.activity.model.aggregate.CreateQuotaOrderAggregate;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-15
 * @Description: 交易策略接口
 */
public interface ITradePolicy {
    void trade(CreateQuotaOrderAggregate createQuotaOrderAggregate);
}
