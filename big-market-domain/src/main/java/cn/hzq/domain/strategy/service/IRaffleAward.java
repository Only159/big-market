package cn.hzq.domain.strategy.service;

import cn.hzq.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/3/16
 * @Description 策略奖品查询接口
 **/
public interface IRaffleAward {

    /**
     * 根据策略Id查询抽奖奖品列表配置
     * @param strategyId 策略Id
     * @return 奖品列表
     */
    List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategyId);
}
