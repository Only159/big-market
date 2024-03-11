package cn.hzq.domain.strategy.repository;

import cn.hzq.domain.strategy.model.entity.StrategyAwardEntity;

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

    void storeStrategyAwardSearchRateTable(Long strategyId, BigDecimal rateRange, HashMap<Integer, Integer> strategyAwardSearchRateTable);

    int getRateRange(Long strategyId);

    Integer getStrategyAwardAssemble(Long strategyId, int rateKey);
}
