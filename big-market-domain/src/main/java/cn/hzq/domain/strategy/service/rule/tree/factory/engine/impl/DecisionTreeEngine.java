package cn.hzq.domain.strategy.service.rule.tree.factory.engine.impl;

import cn.hzq.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.hzq.domain.strategy.model.valobj.RuleTreeNodeLineVO;
import cn.hzq.domain.strategy.model.valobj.RuleTreeNodeVO;
import cn.hzq.domain.strategy.model.valobj.RuleTreeVO;
import cn.hzq.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.hzq.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import cn.hzq.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 黄照权
 * @Date 2024/3/14
 * @Description 决策树引擎
 **/
@Slf4j
public class DecisionTreeEngine implements IDecisionTreeEngine {
    private final Map<String, ILogicTreeNode> logicTreeNodeGroup;
    private final RuleTreeVO ruleTreeVO;

    public DecisionTreeEngine(Map<String, ILogicTreeNode> logicTreeNodeGroup, RuleTreeVO ruleTreeVO) {
        this.logicTreeNodeGroup = logicTreeNodeGroup;
        this.ruleTreeVO = ruleTreeVO;
    }

    @Override
    public DefaultTreeFactory.StrategyAwardVO process(String userId, Long strategyId, Integer awardId, Date endDateTime) {
        DefaultTreeFactory.StrategyAwardVO strategyAwardData = null;

        // 获取基础信息
        String nextNode = ruleTreeVO.getTreeRootRuleNode();
        Map<String, RuleTreeNodeVO> treeNodeMap = ruleTreeVO.getTreeNodeMap();

        // 获取起始节点「根节点记录了第一个要执行的规则」
        RuleTreeNodeVO ruleTreeNodeVO = treeNodeMap.get(nextNode);
        while (null != nextNode){
            // 获取决策节点
            ILogicTreeNode logicTreeNode = logicTreeNodeGroup.get(ruleTreeNodeVO.getRuleKey());
            String ruleValue = ruleTreeNodeVO.getRuleValue();
            // 决策节点计算
            DefaultTreeFactory.TreeActionEntity logicEntity = logicTreeNode.logic(userId, strategyId, awardId,ruleValue,endDateTime);
            RuleLogicCheckTypeVO ruleLogicCheckTypeVO = logicEntity.getRuleLogicCheckType();
            strategyAwardData = logicEntity.getStrategyAwardVO();
            log.info("决策树引擎【{}】treeId:{} node:{} code:{}", ruleTreeVO.getTreeName(), ruleTreeVO.getTreeId(), nextNode, ruleLogicCheckTypeVO.getCode());

            // 获取下个节点
            nextNode = nextNode(ruleLogicCheckTypeVO.getCode(), ruleTreeNodeVO.getTreeNodeLineVOList());
            ruleTreeNodeVO = treeNodeMap.get(nextNode);

        }
        //返回最终结果
        return  strategyAwardData;
    }

    /**
     * 寻找下一个节点
     * @param matterValue 事件结果值【放行/接管】
     * @param ruleTreeNodeLineVOList 节点连线列表
     * @return 下一个节点key
     */
    private String nextNode(String matterValue, List<RuleTreeNodeLineVO> ruleTreeNodeLineVOList){
        //规则节点连线为空 代表没有下级节点
        if (null == ruleTreeNodeLineVOList || ruleTreeNodeLineVOList.isEmpty()) return null;
        //通过规则节点连线找下一个节点
        for (RuleTreeNodeLineVO nodeLine : ruleTreeNodeLineVOList) {
            if (decisionLogic(matterValue,nodeLine)){
                return nodeLine.getRuleNodeTo();
            }
        }
        //throw new RuntimeException("决策树引擎，nextNode 计算失败，未找到可执行节点！");
        return null;
    }

    /**
     * 判断当前节点连线的事件值是否与传递的事件结果值相同
     * @param matterValue 事件结果值【放行/接管】
     * @param nodeLine 节点连线
     * @return boolean
     */
    public boolean decisionLogic(String matterValue, RuleTreeNodeLineVO nodeLine){
        switch (nodeLine.getRuleLimitType()){
            case EQUAL:
                //通过上一级的结果【放行/接管】通过规则连线找下一个节点
                return matterValue.equals(nodeLine.getRuleLimitValue().getCode());
            // TODO 后续实现
            case GT:
            case LT:
            case GE:
            case LE:
            default: return false;
        }
    }
}
