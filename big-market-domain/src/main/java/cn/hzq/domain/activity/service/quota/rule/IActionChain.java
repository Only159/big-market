package cn.hzq.domain.activity.service.quota.rule;

import cn.hzq.domain.activity.model.entity.ActivityCountEntity;
import cn.hzq.domain.activity.model.entity.ActivityEntity;
import cn.hzq.domain.activity.model.entity.ActivitySkuEntity;

/**
 * @author 黄照权
 * @Date 2024/4/25
 * @Description 抽奖规则过滤接口
 **/
public interface IActionChain extends IActionChainArmory{
    boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);
}
