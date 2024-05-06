package cn.hzq.domain.activity.service;

import cn.hzq.domain.activity.model.entity.PartakeRaffleActivityEntity;
import cn.hzq.domain.activity.model.entity.UserRaffleOrderEntity;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 抽奖活动参与服务接口
 **/
public interface IRaffleActivityPartakeService {
    /**
     * 创建抽奖单；用户参与抽奖活动，扣减账户库存，产生抽奖单，如果存在未使用的抽奖当则直接返回已存在的抽奖单
     *
     * @param partakeRaffleActivityEntity 参与抽奖活动的实体对象
     * @return 用户抽奖订单实体对象
     */
    UserRaffleOrderEntity createOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity);

    /**
     * 创建抽奖单；用户参与抽奖活动，扣减账户库存，产生抽奖单，如果存在未使用的抽奖当则直接返回已存在的抽奖单
     *
     * @param userId     用户Id
     * @param activityId 活动Id
     * @return 用户抽奖订单实体对象
     */
    UserRaffleOrderEntity createOrder(String userId, Long activityId);

}
