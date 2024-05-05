package cn.hzq.domain.activity.repository;

import cn.hzq.domain.activity.model.aggregate.CreatePartakeOrderAggregate;
import cn.hzq.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import cn.hzq.domain.activity.model.entity.*;
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
     * @param createQuotaOrderAggregate 下单聚合对象
     */
    void doSaveOrder(CreateQuotaOrderAggregate createQuotaOrderAggregate);

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

    /**
     * 通过参与抽奖活动实体对象 查找未使用订单
     * @param partakeRaffleActivityEntity 参与抽奖活动实体对象
     * @return 抽奖订单实体对象
     */
    UserRaffleOrderEntity queryNoUserRaffleOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity);

    /**
     * 保存参与活动订单实体对象
     * @param createPartakeOrderAggregate 参与活动订单聚合对象
     */
    void saveCreatePartakeOrderAggregate(CreatePartakeOrderAggregate createPartakeOrderAggregate);

    /**
     * 通过用户Id查找活动账户日额度实体对象
     * @param userId 用户Id
     * @param activityId 活动Id
     * @param day 时间（天）
     * @return 活动账户（日） 实体对象
     */
    ActivityAccountDayEntity queryActivityAccountDayByUserId(String userId, Long activityId, String day);

    /**
     * 通过用户Id查找活动账户月额度实体对象
     * @param userId 用户Id
     * @param activityId 活动Id
     * @param month 时间（月）
     * @return 活动账户（月）额度实体对象
     */
    ActivityAccountMonthEntity queryActivityAccountMonthByUserId(String userId, Long activityId, String month);

    /**
     * 通过用户Id查找活动账户总额度实体对象
     * @param userId 用户Id
     * @param activityId 活动Id
     * @return 活动账户（总）额度实体对象
     */
    ActivityAccountEntity queryActivityAccountByUserId(String userId, Long activityId);
}
