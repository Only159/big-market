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
    RaffleActivity queryRaffleActivityByActivityId(Long activityId);
}
