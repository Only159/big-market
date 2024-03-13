package cn.hzq.domain.strategy.service.rule.chain.impl;

import cn.hzq.domain.strategy.repository.IStrategyRepository;
import cn.hzq.domain.strategy.service.armory.IStrategyDispatch;
import cn.hzq.domain.strategy.service.rule.chain.AbstractLogicChain;
import cn.hzq.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 黄照权
 * @Date 2024/3/13
 * @Description 权重
 **/
@Component("rule_weight")
@Slf4j
public class RuleWeightLogicChain extends AbstractLogicChain {
    @Resource
    private IStrategyRepository repository;
    @Resource
    private IStrategyDispatch strategyDispatch;
    public Long userScore = 0L;

    @Override
    public Integer logic(String userId, Long strategyId) {
        log.info("抽奖责任链-权重开始 userId:{} strategy:{} ruleModel:{}", userId,strategyId,ruleModel());

        //查询权重配置信息
        String ruleValue = repository.queryStrategyRuleValue(strategyId, ruleModel());

        // 1. 根据用户ID查询用户抽奖消耗的积分 目前写死，后续需要从数据库查 TODO：

        Map<Long, List<Integer>> analyticalValueGroup = getAnalyticalValue(ruleValue);
        if (null == analyticalValueGroup || analyticalValueGroup.isEmpty()) return  null;

        // 2. 转换Keys值，并默认排序
        List<Long> analyticalSortedKeys = new ArrayList<>(analyticalValueGroup.keySet());
        Collections.sort(analyticalSortedKeys);

        // 3. 找出最小符合的值，也就是【4500 积分，能找到 4000:102,103,104,105】、【5500 积分，能找到 5000:102,103,104,105,106,107】
        Long nextValue = analyticalSortedKeys.stream()
                .filter(key -> userScore >= key)
                .max(Comparator.naturalOrder())
                .orElse(null);
        if (null != nextValue){
            Integer awardId = strategyDispatch.getRandomAwardId(strategyId, nextValue.toString());
            log.info("抽奖责任链-权重接管 userId:{} strategy:{} ruleModel:{} award:{}", userId,strategyId,ruleModel(),awardId);
            return awardId;
        }
        log.info("抽奖责任链-权重放行 userId:{} strategy:{} ruleModel:{}", userId,strategyId,ruleModel());
        return next().logic(userId,strategyId);
    }

    @Override
    protected String ruleModel() {
        return "rule_weight";
    }

    private Map<Long, List<Integer>> getAnalyticalValue(String ruleValue) {
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<Long, List<Integer>> resultMap = new HashMap<>();
        for (String ruleValueGroup : ruleValueGroups) {
            //检查输入是否为空
            if (ruleValueGroup == null || ruleValueGroup.isEmpty()) return resultMap;

            //分割字符串获取键和值
            String[] parts = ruleValueGroup.split(Constants.COLON);
            if (parts.length != 2) {
                throw new IllegalArgumentException("rule_weight rule_rule invalid input format" + ruleValueGroup);
            }
            //解析值
            String[] valueStrings = parts[1].split(Constants.SPLIT);
            ArrayList<Integer> values = new ArrayList<>();
            for (String valueString : valueStrings) {
                values.add(Integer.parseInt(valueString));
            }
            resultMap.put(Long.parseLong(parts[0]), values);
        }
        return resultMap;
    }
}
