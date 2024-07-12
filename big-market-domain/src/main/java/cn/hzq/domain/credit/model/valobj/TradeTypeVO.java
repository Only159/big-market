package cn.hzq.domain.credit.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-11
 * @Description: 交易类型枚举值
 */
@Getter
@AllArgsConstructor
public enum TradeTypeVO {
    FORWARD("forward", "正向交易，+ 积分"),
    REVERSE("reverse", "逆向交易，- 积分"),
    ;

    private final String code;
    private final String info;

}
