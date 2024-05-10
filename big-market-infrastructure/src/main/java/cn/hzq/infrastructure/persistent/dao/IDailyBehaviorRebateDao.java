package cn.hzq.infrastructure.persistent.dao;

import cn.hzq.infrastructure.persistent.po.DailyBehaviorRebate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/10
 * @Description 日常行为返利活动配置
 **/
@Mapper
public interface IDailyBehaviorRebateDao {
    List<DailyBehaviorRebate> queryDailyBehaviorRebateByBehaviorType(String behaviorType);
}
