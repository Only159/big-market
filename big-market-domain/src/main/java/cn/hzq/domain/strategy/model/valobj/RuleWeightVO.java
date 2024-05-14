package cn.hzq.domain.strategy.model.valobj;

import lombok.*;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/14
 * @Description 权重规则值对象
 **/
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleWeightVO {
    /**
     * 权重规则值
     */
    private String ruleValue;
    /**
     * 权重值
     */
    private Integer weight;
    /**
     * 奖品Id
     */
    private List<Integer> awardIds;
    /**
     * 奖品信息
     */
    private List<Award> awardList;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Award {
        /**
         * 奖品Id
         */
        private Integer awardId;
        /**
         * 奖品标题
         */
        private String awardTitle;
    }
}
