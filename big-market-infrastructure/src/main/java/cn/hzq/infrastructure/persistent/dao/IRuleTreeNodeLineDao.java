package cn.hzq.infrastructure.persistent.dao;

import cn.hzq.infrastructure.persistent.po.RuleTreeNodeLine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/3/14
 * @Description  规则树节点线[from -> to] DAO
 **/
@Mapper
public interface IRuleTreeNodeLineDao {
    List<RuleTreeNodeLine> queryRuleTreeNodeLineList();

    List<RuleTreeNodeLine> queryRuleTreeNodeLineListByTreeId(String treeId);
}
