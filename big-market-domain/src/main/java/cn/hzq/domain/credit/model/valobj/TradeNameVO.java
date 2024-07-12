package cn.hzq.domain.credit.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-11
 * @Description: 交易名称枚举值
 */
@Getter
@AllArgsConstructor
public enum TradeNameVO {
    REBATE("行为返利"),
    CONVERT_SKU("兑换抽奖"),
    ;
    private final String name;

}
