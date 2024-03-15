package cn.hzq.domain.strategy.service.raffle;

import cn.hzq.domain.strategy.model.entity.StrategyAwardStockKeyVO;
import cn.hzq.domain.strategy.model.valobj.RuleTreeVO;
import cn.hzq.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import cn.hzq.domain.strategy.repository.IStrategyRepository;
import cn.hzq.domain.strategy.service.AbstractRaffleStrategy;
import cn.hzq.domain.strategy.service.armory.IStrategyDispatch;
import cn.hzq.domain.strategy.service.rule.chain.ILogicChain;
import cn.hzq.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.hzq.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import cn.hzq.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 黄照权
 * @Date 2024/3/13
 * @Description 默认的抽奖策略实现
 **/
@Slf4j
@Service
public class DefaultRaffleStrategy extends AbstractRaffleStrategy {

    public DefaultRaffleStrategy(IStrategyRepository repository, IStrategyDispatch strategyDispatch, DefaultChainFactory defaultChainFactory, DefaultTreeFactory defaultTreeFactory) {
        super(repository, strategyDispatch, defaultChainFactory, defaultTreeFactory);
    }

    @Override
    public DefaultChainFactory.StrategyAwardVO raffleLogicChain(String userId, Long strategyId) {
        //获取责任链
        ILogicChain iLogicChain = defaultChainFactory.openLogicChain(strategyId);
        //返回抽奖结果
        return iLogicChain.logic(userId, strategyId);
    }

    @Override
    public DefaultTreeFactory.StrategyAwardVO raffleLogicTree(String userId, Long strategyId, Integer awardId) {
        //根据策略ID和奖品ID获取奖品规则
        StrategyAwardRuleModelVO strategyAwardRuleModelVO = repository.queryStrategyAwardListRuleModelVO(strategyId, awardId);
        //没有配置 直接返回结果
        if (null == strategyAwardRuleModelVO) {
            return DefaultTreeFactory.StrategyAwardVO.builder()
                    .awardId(awardId)
                    .build();
        }
        //查询规则树
        RuleTreeVO ruleTreeVO = repository.queryRuleTreeVOByTreeId(strategyAwardRuleModelVO.getRuleModels());
        //配置规则，没有配置规则明细信息
        if (null == ruleTreeVO) {
            throw new RuntimeException("存在抽奖策略配置的规则模型Key，未在库表中配置rule_tree,rule_tree_node,rule_tree_node_line对应的规则树信息");
        }
        //根据规则树过滤结果
        IDecisionTreeEngine iDecisionTreeEngine = defaultTreeFactory.openLogicTree(ruleTreeVO);
        return iDecisionTreeEngine.process(userId, strategyId, awardId);

    }

    @Override
    public StrategyAwardStockKeyVO takeQueueValue() throws InterruptedException {
        return repository.takeQueueValue();
    }

    @Override
    public void updateStrategyAwardStock(Long strategyId, Integer awardId) {
        repository.updateStrategyAwardStock(strategyId,awardId);
    }
}

