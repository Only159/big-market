package cn.hzq.domain.activity.service;

import cn.hzq.domain.activity.model.entity.ActivityAccountEntity;
import cn.hzq.domain.activity.model.entity.DeliveryOrderEntity;
import cn.hzq.domain.activity.model.entity.SkuRechargeEntity;

/**
 * @author 黄照权
 * @Date 2024/4/24
 * @Description 抽奖活动账户额度服务
 **/
public interface IRaffleActivityAccountQuotaService {
    /**
     * 创建 sku 账户充值订单，给用户增加抽奖次数
     * <p>
     * 1. 在【打卡、签到、分享、对话、积分兑换】等行为动作下，创建出活动订单，给用户的活动账户【日、月】充值可用的抽奖次数。
     * 2. 对于用户可获得的抽奖次数，比如首次进来就有一次，则是依赖于运营配置的动作，在前端页面上。用户点击后，可以获得一次抽奖次数。
     *
     * @param skuRechargeEntity 活动商品充值实体对象
     * @return 活动ID createOrder
     */
    String createOrder(SkuRechargeEntity skuRechargeEntity);

    /**
     * 更新订单
     * @param deliveryOrderEntity 出货单实体
     */
    void updateOrder(DeliveryOrderEntity deliveryOrderEntity);

    /**
     * 查询用户当天已经参加的抽奖次数
     *
     * @param activityId 活动Id
     * @param userId     用户Id
     * @return 用于已经参加的抽奖次数
     */

    Integer queryRaffleActivityAccountDayPartakeCount(Long activityId, String userId);

    /**
     * 查询活动账户次数
     *
     * @param userId     用户Id
     * @param activityId 活动Id
     * @return 活动账户实体对象
     */
    ActivityAccountEntity queryActivityAccountEntity(String userId, Long activityId);

    /**
     * 查询用户已经参加的抽奖次数
     *
     * @param activityId 活动Id
     * @param userId     用户Id
     * @return 用户已经参加的抽奖次数
     */
    Integer queryRaffleActivityAccountPartakeCount(String userId, Long activityId);
}