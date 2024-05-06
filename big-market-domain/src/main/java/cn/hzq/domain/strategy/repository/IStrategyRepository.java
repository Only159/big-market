package cn.hzq.domain.strategy.repository;

import cn.hzq.domain.strategy.model.entity.StrategyAwardEntity;
import cn.hzq.domain.strategy.model.entity.StrategyAwardStockKeyVO;
import cn.hzq.domain.strategy.model.entity.StrategyEntity;
import cn.hzq.domain.strategy.model.entity.StrategyRuleEntity;
import cn.hzq.domain.strategy.model.valobj.RuleTreeVO;
import cn.hzq.domain.strategy.model.valobj.StrategyAwardRuleModelVO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/3/11
 * @Description 策略服务仓储接口
 **/
public interface IStrategyRepository {
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void storeStrategyAwardSearchRateTable(String key, BigDecimal rateRange, HashMap<Integer, Integer> strategyAwardSearchRateTable);

    int getRateRange(Long strategyId);

    int getRateRange(String key);

    Integer getStrategyAwardAssemble(String key, int rateKey);

    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, String ruleModel);

    StrategyAwardRuleModelVO queryStrategyAwardListRuleModelVO(Long strategyId, Integer awardId);

    /**
     * 根据规则树ID，查询树结构信息
     *
     * @param treeId 规则树ID
     * @return 树结构信息
     */
    RuleTreeVO queryRuleTreeVOByTreeId(String treeId);

    /**
     * 缓存奖品库存
     *
     * @param cacheKey   key
     * @param awardCount 库存值
     */
    void cacheStrategyAwardCount(String cacheKey, Integer awardCount);

    /**
     * 缓存key，decr方式扣减库存
     *
     * @param cacheKey key
     * @return 扣减结果
     */
    Boolean subtractionAwardStock(String cacheKey);

    /**
     * 写入奖品库存消费队列
     *
     * @param strategyAwardStockKeyVO 对象值对象
     */
    void awardStockConsumeSendQueue(StrategyAwardStockKeyVO strategyAwardStockKeyVO);

    /**
     * 获取奖品库存消费队列
     */
    StrategyAwardStockKeyVO takeQueueValue() throws InterruptedException;

    /**
     * 更新奖品库存消耗
     *
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     */
    void updateStrategyAwardStock(Long strategyId, Integer awardId);

    /**
     * 根据策略Id+奖品Id的唯一值组合,查询奖品信息
     * @param strategyId 策略Id
     * @param awardId 奖品Id
     * @return 奖品信息
     */
    StrategyAwardEntity queryStrategyAwardEntity(Long strategyId, Integer awardId);

    /**
     * 通过活动Id查询策略Id
     * @param activityId 活动Id
     * @return 策略Id
     */
    Long queryStrategyIdByActivityId(Long activityId);

    /**
     * 通过策略Id和 用户Id查询用户当日可以用抽奖次数
     * @param userId 用户id
     * @param strategyId 策略Id
     * @return 用户可用抽奖次数
     */
    Integer queryTodayUserRaffleCount(String userId, Long strategyId);
}
