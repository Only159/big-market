package cn.hzq.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/3/16
 * @Description 抽奖应答结果
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaffleStrategyResponseDTO {

    /**
     * 奖品Id
     */
    private Integer awardId;
    /**
     * 排序编号【策略奖品配置的奖品顺序编号】
     */
    private Integer awardIndex;
}
