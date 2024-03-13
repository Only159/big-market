package cn.hzq.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/3/14
 * @Description 规则树节点指向线对象 用于衔接 form->to 节点链路关系
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeLineVO {
    /**
     * 规则树ID
     */
    private Integer treeId;
    /**
     *  节点从哪开始 From 【值为规则Key】
     */
    private String ruleNodeFrom;
    /**
     * 规则Key节点 To
     */
    private String ruleNodeTo;
    /**
     * 限定类型; 1:=; 2:>; 3:< ;4:>=; 5:<=; 6:enum【枚举】
     */
    private RuleLimitTypeVO ruleLimitType;
    /**
     * 限定值（到下一个节点）【接管或放行】
     */
    private RuleLogicCheckTypeVO ruleLimitValue;
}
