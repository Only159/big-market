package cn.hzq.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/5/14
 * @Description 查询抽奖策略权重
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaffleStrategyRuleWeightRequestDTO {
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 活动Id
     */
    private Long activityId;
}
