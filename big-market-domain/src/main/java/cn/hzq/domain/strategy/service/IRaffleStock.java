package cn.hzq.domain.strategy.service;

import cn.hzq.domain.strategy.model.entity.StrategyAwardStockKeyVO;

/**
 * @author 黄照权
 * @Date 2024/3/15
 * @Description 抽奖库存相关服务，获取库存消费队列
 **/
public interface IRaffleStock {
    /**
     * 获取奖品库存消费队列
     */
    StrategyAwardStockKeyVO takeQueueValue() throws InterruptedException;

    /**
     * 更新奖品库存消耗
     *
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     */
    void updateStrategyAwardStock(Long strategyId, Integer awardId);
}
