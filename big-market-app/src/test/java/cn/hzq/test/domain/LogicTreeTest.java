package cn.hzq.test.domain;

import cn.hzq.domain.strategy.model.valobj.*;
import cn.hzq.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import cn.hzq.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 黄照权
 * @Date 2024/3/14
 * @Description 规则树测试
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogicTreeTest {

    @Resource
    private DefaultTreeFactory defaultTreeFactory;

    /**
     * rule_lock --左--> rule_luck_award
     *           --右--> rule_stock --右--> rule_luck_award
     */

    @Test
    public void test_tree_rule() {
        RuleTreeNodeVO rule_luck_award = RuleTreeNodeVO.builder()
                .treeId(10000001)
                .ruleKey("rule_luck_award")
                .ruleDesc("限定用户已完成N次后就后解锁")
                .ruleValue("1")
                .treeNodeLineVOList(null)
                .build();
        RuleTreeNodeVO rule_lock = RuleTreeNodeVO.builder()
                .treeId(10000001)
                .ruleKey("rule_lock")
                .ruleDesc("限定用户已完成N次后就后解锁")
                .ruleValue("1")
                .treeNodeLineVOList(new ArrayList<RuleTreeNodeLineVO>() {{
                    add(RuleTreeNodeLineVO.builder()
                            .treeId(10000001)
                            .ruleNodeFrom("rule_lock")
                            .ruleNodeTo("rule_luck_award")
                            .ruleLimitType(RuleLimitTypeVO.EQUAL)
                            .ruleLimitValue(RuleLogicCheckTypeVO.TAKE_OVER)
                            .build());
                    add(RuleTreeNodeLineVO.builder()
                            .treeId(10000001)
                            .ruleNodeFrom("rule_lock")
                            .ruleNodeTo("rule_stock")
                            .ruleLimitType(RuleLimitTypeVO.EQUAL)
                            .ruleLimitValue(RuleLogicCheckTypeVO.ALLOW)
                            .build());

                }})
                .build();
        RuleTreeNodeVO rule_stock = RuleTreeNodeVO.builder()
                .treeId(10000001)
                .ruleKey("rule_stock")
                .ruleDesc("库存处理规则")
                .ruleValue(null)
                .treeNodeLineVOList(new ArrayList<RuleTreeNodeLineVO>() {{
                    add(RuleTreeNodeLineVO.builder()
                            .treeId(10000001)
                            //.ruleNodeFrom("rule_lock")
                            .ruleNodeFrom("rule_stock")
                            .ruleNodeTo("rule_luck_award")
                            .ruleLimitType(RuleLimitTypeVO.EQUAL)
                            .ruleLimitValue(RuleLogicCheckTypeVO.TAKE_OVER)
                            .build());
                }})
                .build();
        RuleTreeVO ruleTreeVO = new RuleTreeVO();
        ruleTreeVO.setTreeId(10000001);
        ruleTreeVO.setTreeName("决策树规则,增加all-e-3画图模型");
        ruleTreeVO.setTreeDesc("决策树规则,增加all-e-3画图模型");
        ruleTreeVO.setTreeRootRuleNode("rule_lock");

        ruleTreeVO.setTreeNodeMap(new HashMap<String,RuleTreeNodeVO>(){{
            put("rule_lock",rule_lock);
            put("rule_stock",rule_stock);
            put("rule_luck_award",rule_luck_award);
        }});

        IDecisionTreeEngine treeEngine = defaultTreeFactory.openLogicTree(ruleTreeVO);
        DefaultTreeFactory.StrategyAwardData data = treeEngine.process("hzq", 10001L, 1000);
        log.info("测试结果：{}", JSON.toJSONString(data));
    }
}
