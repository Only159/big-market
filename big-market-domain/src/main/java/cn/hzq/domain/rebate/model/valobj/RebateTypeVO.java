package cn.hzq.domain.rebate.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 黄照权
 * @Date 2024/5/11
 * @Description 返利类型 （SKU 活动库存商品，integral 用户活动积分）
 **/
@Getter
@AllArgsConstructor
public enum RebateTypeVO {
    SKU("sku", "活动库存商品"),
    INTEGRAL("integral", "用户活动积分"),
    ;
    private final String code;
    private final String info;
}
