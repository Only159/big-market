package cn.hzq.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import cn.hzq.infrastructure.persistent.po.UserRaffleOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 用户抽奖订单表
 **/
@Mapper
@DBRouterStrategy(splitTable = true) //作用是执行 MyBaits 操作的时候，对 SQL 语句进行动态变更
public interface IUserRaffleOrderDao {
    void insert(UserRaffleOrder userRaffleOrder);

    /**
     * 查询未使用的订单
     * @param userRaffleOrderReq 抽奖订单实体对象
     * @return 抽奖订单实体对象
     */

    @DBRouter
    UserRaffleOrder queryNoUsedRaffleOrder(UserRaffleOrder userRaffleOrderReq);

    /**
     * 更新抽奖订单状态为已完成
     * @param userRaffleOrderReq 抽奖订单实体对象
     * @return 更新结果（受影响行数）
     */
    int updateUserRaffleOrderStateUsed(UserRaffleOrder userRaffleOrderReq);
}
