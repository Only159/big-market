package cn.hzq.infrastructure.persistent.dao;

import cn.hzq.infrastructure.persistent.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/3/10
 * @Description 抽奖策略 DAO
 **/
@Mapper
public interface IStrategyDao {
    List<Strategy> queryStrategyList();
}
