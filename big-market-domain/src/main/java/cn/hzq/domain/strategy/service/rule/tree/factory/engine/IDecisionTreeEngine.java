package cn.hzq.domain.strategy.service.rule.tree.factory.engine;

import cn.hzq.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author 黄照权
 * @Date 2024/3/14
 * @Description 规则树组合接口
 **/
public interface IDecisionTreeEngine {
    DefaultTreeFactory.StrategyAwardData process(String userId, Long strategyId, Integer awardId);
}
