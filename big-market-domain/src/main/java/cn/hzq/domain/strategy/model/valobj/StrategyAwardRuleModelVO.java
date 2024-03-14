package cn.hzq.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/3/13
 * @Description 抽奖策略规则规则值对象；值对象 没有唯一Id，仅限从数据库查询对象
 **/
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardRuleModelVO {

    private String ruleModels;

}
