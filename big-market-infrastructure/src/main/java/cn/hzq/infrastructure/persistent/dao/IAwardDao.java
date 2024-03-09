package cn.hzq.infrastructure.persistent.dao;

import cn.hzq.infrastructure.persistent.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/3/10
 * @Description 奖品表 DAO
 **/
@Mapper
public interface IAwardDao {
    List<Award> queryAwardList();
}
