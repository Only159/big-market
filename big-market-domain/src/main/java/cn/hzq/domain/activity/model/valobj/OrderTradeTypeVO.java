package cn.hzq.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-15
 * @Description: 订单交易类型枚举类
 */
@Getter
@AllArgsConstructor
public enum OrderTradeTypeVO {
    credit_pay_trade("credit_pay_trade", "积分兑换，需要支付类交易"),
    rebate_no_pay_trade("rebate_no_pay_trade", "返利奖品，无需支付类交易"),
    ;
    private final String code;
    private final String desc;
}
