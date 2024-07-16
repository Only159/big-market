package cn.hzq.domain.credit.service;

import cn.hzq.domain.credit.model.entity.CreditAccountEntity;
import cn.hzq.domain.credit.model.entity.TradeEntity;

/**
 * 积分服务接口
 */
public interface ICreditAdjustService {
    /**
     * 创建增加积分额度订单
     *
     * @param tradeEntity 交易实体对象
     * @return 单号
     */
    String createOrder(TradeEntity tradeEntity);

    /**
     * 查询用户积分账户
     *
     * @param userId 用户ID
     * @return 积分账户实体
     */
    CreditAccountEntity queryUserCreditAccount(String userId);
}
