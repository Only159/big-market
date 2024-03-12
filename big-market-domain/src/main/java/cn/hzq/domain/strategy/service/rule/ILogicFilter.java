package cn.hzq.domain.strategy.service.rule;

import cn.hzq.domain.strategy.model.entity.RuleActionEntity;
import cn.hzq.domain.strategy.model.entity.RuleMatterEntity;

/**
 * @author 黄照权
 * @Date 2024/3/13
 * @Description 抽奖规则过滤接口
 **/
public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {

    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);

}

