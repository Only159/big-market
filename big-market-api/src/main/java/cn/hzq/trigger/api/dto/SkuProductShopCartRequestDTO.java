package cn.hzq.trigger.api.dto;

import lombok.Data;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-16
 * @Description: 商品购物车请求对象
 */
@Data
public class SkuProductShopCartRequestDTO {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * sku 商品
     */
    private Long sku;

}
