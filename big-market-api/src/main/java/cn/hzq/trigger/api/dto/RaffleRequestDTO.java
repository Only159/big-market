package cn.hzq.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/3/16
 * @Description 抽奖请求参数
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaffleRequestDTO {
    /**
     * 抽奖策略Id
     */
    private Long strategyId;

}
