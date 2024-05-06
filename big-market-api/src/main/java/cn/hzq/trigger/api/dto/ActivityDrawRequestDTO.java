package cn.hzq.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/5/6
 * @Description 活动抽奖请求对象
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityDrawRequestDTO {
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 活动Id
     */
    private Long activityId;
}
