package cn.hzq.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import cn.hzq.infrastructure.persistent.po.UserCreditOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-09
 * @Description: 用户积分流水单 DAO
 */
@Mapper
@DBRouterStrategy(splitTable = true) //作用是执行 MyBaits 操作的时候，对 SQL 语句进行动态变更
public interface IUserCreditOrderDao {
    void insert(UserCreditOrder userCreditOrder);
}
