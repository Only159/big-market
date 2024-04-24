package cn.hzq.domain.activity.service;

import cn.hzq.domain.activity.model.entity.ActivityOrderEntity;
import cn.hzq.domain.activity.model.entity.ActivityShopCartEntity;

/**
 * @author 黄照权
 * @Date 2024/4/24
 * @Description 活动订单接口
 **/
public interface IRaffleOrder {
    /**
     * 以SKU创建活动订单，获得参与抽奖资格（可消耗次数）
     *
     * @param activityShopCartEntity 活动sku实体，通过sku领取活动
     * @return 活动参与记录实体
     */
    ActivityOrderEntity createRaffleActivityOrder(ActivityShopCartEntity activityShopCartEntity);
}
