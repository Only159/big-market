package cn.hzq.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/3/16
 * @Description 抽奖奖品列表请求对象
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaffleAwardListRequestDTO {
    /**
     * 活动Id
     */
    private Long activityId;
    /**
     * 用户Id
     */
    private String userId;
}
