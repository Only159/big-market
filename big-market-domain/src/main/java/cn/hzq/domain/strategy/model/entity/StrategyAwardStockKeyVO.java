package cn.hzq.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/3/15
 * @Description  策略奖品库存Key标识对象
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardStockKeyVO {
    /** 策略ID */
    private Long strategyId;
    /** 奖品ID */
    private Integer awardId;
}
