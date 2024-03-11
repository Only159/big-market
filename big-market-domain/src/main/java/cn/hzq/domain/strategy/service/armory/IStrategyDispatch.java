package cn.hzq.domain.strategy.service.armory;

/**
 * @author 黄照权
 * @Date 2024/3/11
 * @Description 策略抽奖调度
 **/
public interface IStrategyDispatch {
    /**
     * 获取抽奖策略装配的随机结果
     *
     * @param strategyId 策略ID
     * @return 抽奖结果
     */
    Integer getRandomAwardId(Long strategyId);
    /**
     * 获取抽奖策略装配的随机结果
     *
     * @param strategyId 策略ID
     * @param ruleWeightValue 权重值
     * @return 抽奖结果
     */
    Integer getRandomAwardId(Long strategyId,String ruleWeightValue);
}
