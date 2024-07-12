package cn.hzq.domain.credit.service;

import cn.hzq.domain.credit.model.entity.TradeEntity;

/**
 * 积分服务接口
 */
public interface ICreditAdjustService {
    /**
     * 创建增加积分额度订单
     * @param tradeEntity 交易实体对象
     * @return 单号
     */
    String createOrder(TradeEntity tradeEntity);
}
