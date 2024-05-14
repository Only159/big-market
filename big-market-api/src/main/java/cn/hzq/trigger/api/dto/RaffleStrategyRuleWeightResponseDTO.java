package cn.hzq.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/14
 * @Description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaffleStrategyRuleWeightResponseDTO {
    private Integer ruleWeightCount;
    private Integer userActivityAccountTotalUseCount;
    private List<StrategyAward> strategyAwards;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StrategyAward {
        private Integer awardId;
        private String awardTitle;
    }
}
