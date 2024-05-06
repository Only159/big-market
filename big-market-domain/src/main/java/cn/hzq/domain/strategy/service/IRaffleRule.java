package cn.hzq.domain.strategy.service;

import java.util.Map;

/**
 * @author 黄照权
 * @Date 2024/5/6
 * @Description 抽奖规则接口
 **/
public interface IRaffleRule {
    /**
     * 根据规则Id列表查询对应规则配置
     * @param treeIds 规则Id 列表
     * @return map 配置列表
     */

    Map<String,Integer> queryAwardRuleLockCount(String[] treeIds);
}
