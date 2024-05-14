package cn.hzq.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/5/14
 * @Description 查询活动账户请求参数对象
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserActivityAccountRequestDTO {
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 活动Id
     */
    private Long activityId;
}
