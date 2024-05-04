package cn.hzq.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/5/4
 * @Description 活动sku库存 key 值对象
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySkuStockVO {
    /**
     * 商品sku
     */
    private Long sku;

    /**
     * 活动Id
     */
    private Long activityId;
}
