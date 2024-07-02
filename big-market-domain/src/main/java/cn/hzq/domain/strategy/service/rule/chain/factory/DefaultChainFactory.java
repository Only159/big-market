package cn.hzq.domain.strategy.service.rule.chain.factory;

import cn.hzq.domain.strategy.model.entity.StrategyEntity;
import cn.hzq.domain.strategy.repository.IStrategyRepository;
import cn.hzq.domain.strategy.service.rule.chain.ILogicChain;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 黄照权
 * @Date 2024/3/13
 * @Description 工厂
 **/
@Service
public class DefaultChainFactory {
    private final Map<String, ILogicChain> logicChainGroup;
    private final IStrategyRepository repository;

    public DefaultChainFactory(Map<String, ILogicChain> logicChainGroup, IStrategyRepository repository) {
        this.logicChainGroup = logicChainGroup;
        this.repository = repository;
    }

    /**
     * 通过策略Id 构建责任链
     *
     * @param strategyId 策略ID
     * @return LogicChain
     */

    public ILogicChain openLogicChain(Long strategyId) {
        //获取策略配置ruleModel
        StrategyEntity strategy = repository.queryStrategyEntityByStrategyId(strategyId);
        String[] ruleModels = strategy.ruleModels();
        //策略配置为空 直接返回兜底责任链
        if (null == ruleModels || 0 == ruleModels.length) {
            return logicChainGroup.get("rule_default");
        }
        //策略配置为不为空,创建对应ruleModels的责任链
        ILogicChain logicChain = logicChainGroup.get(ruleModels[0]);
        ILogicChain current = logicChain;
        for (int i = 1; i < ruleModels.length; i++) {
            current = current.appendNext(logicChainGroup.get(ruleModels[i]));
        }
        current.appendNext(logicChainGroup.get("rule_default"));
        return logicChain;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StrategyAwardVO {
        /**
         * 抽奖奖品ID- 内部流转使用
         */
        private Integer awardId;
        /**
         * 抽奖奖品类型【默认抽奖/黑名单抽奖/权重抽奖】
         */
        private String logicModel;
        /**
         * 抽奖奖品规则
         */
        private String awardRuleValue;
    }

    @Getter
    @AllArgsConstructor
    public enum LogicModel {
        RULE_DEFAULT("rule_default", "默认抽奖"),
        RULE_BLACKLIST("rule_blacklist", "黑名单抽奖"),
        RULE_WEIGHT("rule_weight", "权重规则抽奖"),
        ;
        private final String code;
        private final String info;
    }


}
