package cn.hzq.infrastructure.persistent.dao;

import cn.hzq.infrastructure.persistent.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/3/14
 * @Description 规则树 DAO
 **/
@Mapper
public interface IRuleTreeDao {
    List<RuleTree> queryRuleTreeList();

    RuleTree queryRuleTreeByTreeId(String treeId);
}
