package cn.hzq.domain.strategy.service;

import cn.hzq.domain.strategy.model.valobj.RuleWeightVO;

import java.util.List;
import java.util.Map;

/**
 * @author 黄照权
 * @Date 2024/5/6
 * @Description 抽奖规则接口
 **/
public interface IRaffleRule {
    /**
     * 根据规则Id列表查询对应规则配置
     *
     * @param treeIds 规则Id 列表
     * @return map 配置列表
     */

    /**
     * 查询次数锁配置信息
     * @param treeIds 规则树ID
     * @return 次数锁配置信息
     */
    Map<String, Integer> queryAwardRuleLockCount(String[] treeIds);

    /**
     * 通过策略Id查询规则权重配置
     * @param strategyId 策略ID
     * @return 权重配置信息
     */
    List<RuleWeightVO> queryAwardRuleWeight(Long strategyId);

    /**
     * 通过活动Id查询规则权重配置
     * @param activityId 活动Id
     * @return 权重配置信息
     */
    List<RuleWeightVO> queryAwardRuleWeightByActivityId(Long activityId);

}
