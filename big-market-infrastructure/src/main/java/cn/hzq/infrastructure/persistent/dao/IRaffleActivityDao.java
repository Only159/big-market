package cn.hzq.infrastructure.persistent.dao;

import cn.hzq.infrastructure.persistent.po.RaffleActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 黄照权
 * @Date 2024/4/23
 * @Description 抽奖活动表DAO
 **/
@Mapper
public interface IRaffleActivityDao {
    /**
     * 通过活动Id查询活动信息
     * @param activityId 活动Id
     * @return 活动实体对象
     */
    RaffleActivity queryRaffleActivityByActivityId(Long activityId);

    /**
     * 通过活动Id 查询策略Id
     * @param activityId 活动Id
     * @return 策略Id
     */
    Long queryStrategyIdByActivityId(Long activityId);
    /**
     * 通过策略Id查询活动Id
     * @param strategyId 策略Id
     * @return 策略Id
     */
    Long queryActivityIdByStrategyId(Long strategyId);
}
