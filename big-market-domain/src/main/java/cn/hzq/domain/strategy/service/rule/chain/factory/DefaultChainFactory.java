package cn.hzq.domain.strategy.service.rule.chain.factory;

import cn.hzq.domain.strategy.model.entity.StrategyEntity;
import cn.hzq.domain.strategy.repository.IStrategyRepository;
import cn.hzq.domain.strategy.service.rule.chain.ILogicChain;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 黄照权
 * @Date 2024/3/13
 * @Description 工厂
 **/
@Service
public class DefaultChainFactory {
    private final Map<String, ILogicChain> logicChainGroup;
    private final IStrategyRepository repository;

    public DefaultChainFactory(Map<String, ILogicChain> logicChainGroup, IStrategyRepository repository) {
        this.logicChainGroup = logicChainGroup;
        this.repository = repository;
    }

    public ILogicChain openLogicChain(Long strategyId) {
        //获取策略配置ruleModel
        StrategyEntity strategy = repository.queryStrategyEntityByStrategyId(strategyId);
        String[] ruleModels = strategy.ruleModels();
        //策略配置为空 直接返回兜底责任链
        if (null == ruleModels || 0 == ruleModels.length) {
            return logicChainGroup.get("default");
        }
        //策略配置为不为空,创建对应ruleModels的责任链
        ILogicChain logicChain = logicChainGroup.get(ruleModels[0]);
        ILogicChain current = logicChain;
        for (int i = 1; i < ruleModels.length; i++) {
            current = current.appendNext(logicChainGroup.get(ruleModels[i]));
        }
        current.appendNext(logicChainGroup.get("default"));
        return logicChain;
    }

}
