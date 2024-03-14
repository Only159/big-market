package cn.hzq.domain.strategy.service.rule.chain.impl;

import cn.hzq.domain.strategy.repository.IStrategyRepository;
import cn.hzq.domain.strategy.service.rule.chain.AbstractLogicChain;
import cn.hzq.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.hzq.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/3/13
 * @Description 黑名单责任链节点
 **/
@Component("rule_blacklist")
@Slf4j
public class BlackListLogicChain extends AbstractLogicChain {
    @Resource
    private IStrategyRepository repository;

    @Override
    public DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId) {
        log.info("抽奖责任链-黑名单开始 userId:{} strategy:{} ruleModel:{}", userId, strategyId, ruleModel());
        String ruleValue = repository.queryStrategyRuleValue(strategyId, ruleModel());

        String[] splitRuleValue = ruleValue.split(Constants.COLON);
        Integer awardId = Integer.parseInt(splitRuleValue[0]);

        // 过滤其他规则
        String[] userBlackIds = splitRuleValue[1].split(Constants.SPLIT);
        // 遍历用户Id 判断是否在黑名单中
        for (String userBlackId : userBlackIds) {
            if (userId.equals(userBlackId)) {
                log.info("抽奖责任链-黑名单过滤接管 userId:{} strategy:{} ruleModel:{} award:{}", userId, strategyId, ruleModel(), awardId);
                return DefaultChainFactory.StrategyAwardVO.builder()
                        .awardId(awardId)
                        .logicModel(ruleModel())
                        .build();
            }
        }
        log.info("抽奖责任链-黑名单放行 userId:{} strategy:{} ruleModel:{}", userId, strategyId, ruleModel());
        //交给下一个责任链
        return next().logic(userId, strategyId);
    }

    @Override
    protected String ruleModel() {
        return DefaultChainFactory.LogicModel.RULE_BLACKLIST.getCode();
    }
}
