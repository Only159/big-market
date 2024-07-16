package cn.hzq.domain.activity.service;

import cn.hzq.domain.activity.model.entity.SkuProductEntity;

import java.util.List;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-16
 * @Description: sku商品接口服务
 */
public interface IRaffleActivitySkuProductService {
    /**
     * 查询当前活动ID下，创建的 sku 商品。「sku可以兑换活动抽奖次数」
     * @param activityId 活动ID
     * @return 返回sku商品集合
     */
    List<SkuProductEntity> querySkuProductEntityListByActivityId(Long activityId);
}
