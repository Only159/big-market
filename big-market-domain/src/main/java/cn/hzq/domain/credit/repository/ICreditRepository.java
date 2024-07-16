package cn.hzq.domain.credit.repository;

import cn.hzq.domain.credit.model.aggregate.TradeAggregate;
import cn.hzq.domain.credit.model.entity.CreditAccountEntity;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-11
 * @Description: 积分域仓储服务接口
 */
public interface ICreditRepository {
    /**
     * 保存用户交易订单
     *
     * @param tradeAggregate 交易聚合对象
     */
    void saveUserCreditTradeOrder(TradeAggregate tradeAggregate);

    /**
     * 查询用户积分账户
     *
     * @param userId 用户ID
     * @return 积分账户实体
     */
    CreditAccountEntity queryUserCreditAccount(String userId);
}
