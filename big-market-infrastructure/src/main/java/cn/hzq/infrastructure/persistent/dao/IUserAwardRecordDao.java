package cn.hzq.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import cn.hzq.infrastructure.persistent.po.UserAwardRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 用户中奖记录表
 **/
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserAwardRecordDao {
    void insert(UserAwardRecord userAwardRecord);
}
