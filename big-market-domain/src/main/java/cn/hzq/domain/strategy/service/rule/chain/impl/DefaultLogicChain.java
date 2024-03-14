package cn.hzq.domain.strategy.service.rule.chain.impl;

import cn.hzq.domain.strategy.service.armory.IStrategyDispatch;
import cn.hzq.domain.strategy.service.rule.chain.AbstractLogicChain;
import cn.hzq.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/3/13
 * @Description 兜底责任链节点
 **/
@Component("rule_default")
@Slf4j
public class DefaultLogicChain extends AbstractLogicChain {
    @Resource
    private IStrategyDispatch strategyDispatch;
    @Override
    public DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId) {
        Integer awardId = strategyDispatch.getRandomAwardId(strategyId);
        log.info("抽奖责任链-默认处理 userId:{} strategy:{} ruleModel:{} award:{}",
                userId,strategyId,ruleModel(),awardId);
        return DefaultChainFactory.StrategyAwardVO.builder()
                .awardId(awardId)
                .logicModel(ruleModel())
                .build();
    }

    @Override
    protected String ruleModel() {
        return DefaultChainFactory.LogicModel.RULE_DEFAULT.getCode();
    }
}
