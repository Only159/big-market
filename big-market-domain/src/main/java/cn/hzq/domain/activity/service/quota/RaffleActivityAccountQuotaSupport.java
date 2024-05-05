package cn.hzq.domain.activity.service.quota;

import cn.hzq.domain.activity.model.entity.ActivityCountEntity;
import cn.hzq.domain.activity.model.entity.ActivityEntity;
import cn.hzq.domain.activity.model.entity.ActivitySkuEntity;
import cn.hzq.domain.activity.repository.IActivityRepository;
import cn.hzq.domain.activity.service.quota.rule.factory.DefaultActivityChainFactory;

/**
 * @author 黄照权
 * @Date 2024/4/25
 * @Description 抽奖活动支撑类
 **/
public class RaffleActivityAccountQuotaSupport {
    protected DefaultActivityChainFactory defaultActivityChainFactory;
    protected IActivityRepository activityRepository;

    public RaffleActivityAccountQuotaSupport(DefaultActivityChainFactory defaultActivityChainFactory, IActivityRepository activityRepository) {
        this.defaultActivityChainFactory = defaultActivityChainFactory;
        this.activityRepository = activityRepository;
    }

    public ActivitySkuEntity queryActivitySku(Long sku) {
        return activityRepository.queryActivitySkuBySku(sku);
    }

    public ActivityEntity queryRaffleActivityByActivityId(Long activityId) {
        return activityRepository.queryRaffleActivityByActivityId(activityId);
    }

    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId) {
        return activityRepository.queryRaffleActivityCountByActivityCountId(activityCountId);
    }


}
