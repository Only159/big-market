package cn.hzq.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/3/16
 * @Description 抽奖奖品列表 应答对象
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaffleAwardListResponseDTO {
    /**
     * 抽奖奖品ID
     */
    private Integer awardId;
    /**
     * 抽奖奖品标题
     */
    private String awardTitle;
    /**
     * 抽奖奖品副标题
     */
    private String awardSubtitle;
    /**
     * 排序
     */
    private Integer sort;
}
