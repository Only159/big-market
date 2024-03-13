package cn.hzq.domain.strategy.service.rule.tree.factory;

import cn.hzq.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.hzq.domain.strategy.model.valobj.RuleTreeVO;
import cn.hzq.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.hzq.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import cn.hzq.domain.strategy.service.rule.tree.factory.engine.impl.DecisionTreeEngine;
import cn.hzq.domain.strategy.service.rule.tree.impl.RuleLockLogicTreeNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 黄照权
 * @Date 2024/3/14
 * @Description 决策树工场
 **/
@Service
public class DefaultTreeFactory {
    private final Map<String, ILogicTreeNode> logicTreeNodeGroup;

    public DefaultTreeFactory(Map<String, ILogicTreeNode> logicTreeNodeGroup) {
        this.logicTreeNodeGroup = logicTreeNodeGroup;
    }

    public IDecisionTreeEngine openLogicTree(RuleTreeVO ruleTreeVO){
        return new DecisionTreeEngine(logicTreeNodeGroup,ruleTreeVO);
    }

    /**
     * 抽奖结果
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StrategyAwardData {
        /**
         * 抽奖奖品ID- 内部流转使用
         */
        private Integer awardId;
        /**
         * 抽奖奖品规则
         */
        private String awardRuleValue;
    }

    /**
     * 决策树行为 【放行/拦截】
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TreeActionEntity {
        private RuleLogicCheckTypeVO ruleLogicCheckType;
        private StrategyAwardData strategyAwardData;
    }
}
