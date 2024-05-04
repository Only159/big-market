package cn.hzq.domain.activity.service.armory;

import java.util.Date;

/**
 * @author 黄照权
 * @Date 2024/5/4
 * @Description 活动调度【扣减库存】
 **/
public interface IActivityDispatch {
    /**
     * 根据活动sku 和 结束时间扣减库存
     * @param sku 互动SKU
     * @param endDateTime 活动结束时间，根据结束时间设置加锁的key为结束时间
     * @return 扣减结果
     */
    boolean subtractionActivitySkuStock(Long sku, Date endDateTime);
}
