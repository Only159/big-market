package cn.hzq.domain.strategy.service.rule.tree.impl;

import cn.hzq.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.hzq.domain.strategy.repository.IStrategyRepository;
import cn.hzq.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.hzq.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 黄照权
 * @Date 2024/3/14
 * @Description 次数锁节点
 **/
@Slf4j
@Component("rule_lock")
public class RuleLockLogicTreeNode implements ILogicTreeNode {
    @Resource
    private IStrategyRepository repository;

    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue, Date endDateTime) {
        log.info("规则过滤-次数锁 userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
        long raffleCount = 0L;
        //获取次数锁配置
        try {
            raffleCount = Long.parseLong(ruleValue);
        } catch (Exception e) {
            throw new RuntimeException("规则过滤-次数锁【异常】 ruleValue: " + ruleValue + " 配置不正确");
        }
        // 查询抽奖次数- 当天的；策略Id-活动Id 一对一配置  直接用策略Id查询
        Integer userRaffleCount = repository.queryTodayUserRaffleCount(userId, strategyId);
        // 用户抽奖次数大于规则限定值，规则放行
        if (userRaffleCount >= raffleCount) {
            log.info("规则过滤-次数锁 【放行】userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
            return DefaultTreeFactory.TreeActionEntity.builder()
                    .ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                    .build();
        }
        log.info("规则过滤-次数锁 【拦截】userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
        // 用户抽奖次数小于规则限定值，规则拦截
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .build();

    }
}
