package cn.hzq.domain.strategy.service.rule.chain.impl;

import cn.hzq.domain.strategy.service.armory.IStrategyDispatch;
import cn.hzq.domain.strategy.service.rule.chain.AbstractLogicChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/3/13
 * @Description 兜底责任链节点
 **/
@Component("default")
@Slf4j
public class DefaultLogicChain extends AbstractLogicChain {
    @Resource
    private IStrategyDispatch strategyDispatch;
    @Override
    public Integer logic(String userId, Long strategyId) {
        Integer awardId = strategyDispatch.getRandomAwardId(strategyId);
        log.info("抽奖责任链-默认处理 userId:{} strategy:{} ruleModel:{} award:{}",
                userId,strategyId,ruleModel(),awardId);
        return awardId;
    }

    @Override
    protected String ruleModel() {
        return "default";
    }
}
