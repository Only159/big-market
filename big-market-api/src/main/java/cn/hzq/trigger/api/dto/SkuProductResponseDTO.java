package cn.hzq.trigger.api.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-16
 * @Description: sku商品对象响应对象
 */
@Data
public class SkuProductResponseDTO {
    /**
     * 商品sku
     */
    private Long sku;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 活动个人参与次数ID
     */
    private Long activityCountId;
    /**
     * 库存总量
     */
    private Integer stockCount;
    /**
     * 剩余库存
     */
    private Integer stockCountSurplus;
    /**
     * 商品金额【积分】
     */
    private BigDecimal productAmount;

    /**
     * 活动商品数量
     */
    private ActivityCount activityCount;

    @Data
    public static class ActivityCount {
        /**
         * 总次数
         */
        private Integer totalCount;

        /**
         * 日次数
         */
        private Integer dayCount;

        /**
         * 月次数
         */
        private Integer monthCount;
    }

}
