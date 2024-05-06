package cn.hzq.domain.strategy.service.armory;

import java.util.Date;

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

    /**
     * 根据策略ID和奖品ID，扣减奖品缓存库存
     *
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     * @param endDateTime 活动结束时间
     * @return 扣减结果
     */
    Boolean subtractionAwardStock(Long strategyId, Integer awardId, Date endDateTime);
}
