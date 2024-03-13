package cn.hzq.domain.strategy.model.valobj;

import cn.hzq.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import cn.hzq.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 返回抽奖中规则列表
     */
    public String[] raffleCenterRuleModelList() {
        String[] ruleModelValues = ruleModels.split(Constants.SPLIT);
        List<String> ruleModelList = new ArrayList<>();
        for (String ruleModelValue : ruleModelValues) {
            if (DefaultLogicFactory.LogicModel.isCenter(ruleModelValue)) {
                ruleModelList.add(ruleModelValue);
            }
        }
        return ruleModelList.toArray(new String[0]);
    }

    /**
     * 返回抽奖后规则列表
     */
    public String[] raffleAfterRuleModelList() {
        String[] ruleModelValues = ruleModels.split(Constants.SPLIT);
        List<String> ruleModelList = new ArrayList<>();
        for (String ruleModelValue : ruleModelValues) {
            if (DefaultLogicFactory.LogicModel.isCenter(ruleModelValue)) {
                ruleModelList.add(ruleModelValue);
            }
        }
        return ruleModelList.toArray(new String[0]);
    }
}
