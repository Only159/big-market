package cn.hzq.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/5/6
 * @Description 活动抽奖应答对象
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityDrawResponseDTO {
    /**
     * 奖品Id
     */
    private Integer awardId;
    /**
     * 奖品标题
     */
    private String awardTitle;
    /**
     * 排序编号【策略奖品配置的奖品顺序编号】
     */
    private Integer awardIndex;
}
