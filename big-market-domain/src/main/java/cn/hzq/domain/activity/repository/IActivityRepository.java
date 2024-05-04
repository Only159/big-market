package cn.hzq.domain.activity.repository;

import cn.hzq.domain.activity.model.aggregate.CreateOrderAggregate;
import cn.hzq.domain.activity.model.entity.ActivityCountEntity;
import cn.hzq.domain.activity.model.entity.ActivityEntity;
import cn.hzq.domain.activity.model.entity.ActivitySkuEntity;
import cn.hzq.domain.activity.model.valobj.ActivitySkuStockVO;

import java.util.Date;

/**
 * @author 黄照权
 * @Date 2024/4/24
 * @Description 活动仓储接口
 **/
public interface IActivityRepository {
    /**
     * 通过sku获取活动sku实体对象
     *
     * @param sku sku值
     * @return 活动SKU实体对象
     */
    ActivitySkuEntity queryActivitySkuBySku(Long sku);

    /**
     * 通过活动Id获取活动实体对象
     *
     * @param activityId 活动Id
     * @return 活动实体对象
     */
    ActivityEntity queryRaffleActivityByActivityId(Long activityId);

    /**
     * 通过活动次数编号获取活动次数实体对象
     *
     * @param activityCountId 活动次数编号
     * @return 活动次数实体对象
     */
    ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId);

    /**
     * 保存订单
     *
     * @param createOrderAggregate 下单聚合对象
     */
    void doSaveOrder(CreateOrderAggregate createOrderAggregate);

    /**
     * 缓存 sku 库存
     *
     * @param cacheKey   redis 中key关键字
     * @param stockCount 库存
     */
    void cacheActivitySkuStockCount(String cacheKey, Integer stockCount);

    /**
     * 扣减库存
     *
     * @param sku         活动sku
     * @param cacheKey    缓存key
     * @param endDateTime 结束时间（加锁key）
     * @return 扣减结果
     */

    boolean subtractionActivitySkuStock(Long sku, String cacheKey, Date endDateTime);

    /**
     * 写入延迟队列，延迟更新库存
     *
     * @param activitySkuStockVO 消费对象
     */
    void activitySkuStockConsumeSendQueue(ActivitySkuStockVO activitySkuStockVO);

    /**
     * 获取活动消息队列消息对象
     *
     * @return 消费对象
     */
    ActivitySkuStockVO takeQueueValue();

    /**
     * 更新活动库存
     *
     * @param sku 活动sku
     */
    void updateActivitySkuStock(Long sku);

    /**
     * 清空队列值
     */
    void clearQueueValue();

    /**
     * 清空数据库库存值
     *
     * @param sku 活动sku
     */
    void clearActivitySkuStock(Long sku);
}
