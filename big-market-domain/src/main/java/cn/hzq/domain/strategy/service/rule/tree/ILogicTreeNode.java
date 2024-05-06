package cn.hzq.domain.strategy.service.rule.tree;

import cn.hzq.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

import java.util.Date;

/**
 * @author 黄照权
 * @Date 2024/3/14
 * @Description 决策树接口
 **/
public interface ILogicTreeNode {
    DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue, Date endDateTime);
}
