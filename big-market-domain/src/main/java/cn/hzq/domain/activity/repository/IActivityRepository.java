package cn.hzq.domain.activity.repository;

import cn.hzq.domain.activity.model.aggregate.CreateOrderAggregate;
import cn.hzq.domain.activity.model.entity.ActivityCountEntity;
import cn.hzq.domain.activity.model.entity.ActivityEntity;
import cn.hzq.domain.activity.model.entity.ActivitySkuEntity;

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
     * @param createOrderAggregate 下单聚合对象
     */
    void doSaveOrder(CreateOrderAggregate createOrderAggregate);
}
