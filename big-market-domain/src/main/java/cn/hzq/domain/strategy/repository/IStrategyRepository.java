package cn.hzq.domain.strategy.repository;

import cn.hzq.domain.strategy.model.entity.StrategyAwardEntity;
import cn.hzq.domain.strategy.model.entity.StrategyEntity;
import cn.hzq.domain.strategy.model.entity.StrategyRuleEntity;
import cn.hzq.domain.strategy.model.valobj.StrategyAwardRuleModelVo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/3/11
 * @Description 策略仓储接口
 **/
public interface IStrategyRepository {
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void storeStrategyAwardSearchRateTable(String key, BigDecimal rateRange, HashMap<Integer, Integer> strategyAwardSearchRateTable);

    int getRateRange(Long strategyId);
    int getRateRange(String  key);

    Integer getStrategyAwardAssemble(String key, int rateKey);

    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);

    StrategyAwardRuleModelVo queryStrategyAwardListRuleModel(Long strategyId, Integer awardId);
}
