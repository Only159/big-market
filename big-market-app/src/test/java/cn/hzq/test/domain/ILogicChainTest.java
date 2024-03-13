package cn.hzq.test.domain;

import cn.hzq.domain.strategy.model.entity.RaffleAwardEntity;
import cn.hzq.domain.strategy.model.entity.RaffleFactorEntity;
import cn.hzq.domain.strategy.service.IRaffleStrategy;
import cn.hzq.domain.strategy.service.armory.IStrategyArmory;
import cn.hzq.domain.strategy.service.rule.chain.ILogicChain;
import cn.hzq.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.hzq.domain.strategy.service.rule.chain.impl.RuleWeightLogicChain;
import cn.hzq.domain.strategy.service.rule.filter.impl.RuleLockLogicFilter;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @description 抽奖策略测试
 * @create 2024-01-06 13:28
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ILogicChainTest {

    @Resource
    private IStrategyArmory strategyArmory;

    @Resource
    private DefaultChainFactory defaultChainFactory;

    @Resource
    private RuleWeightLogicChain ruleWeightLogicChain;


   @Test
    public void setUp() {
        log.info("策略装配结果：{}", strategyArmory.assembleLotteryStrategy(10001L));
        //log.info("策略装配结果：{}", strategyArmory.assembleLotteryStrategy(10003L));
    }
    @Test
    public void test_LogicChain_rule_blacklist(){
        ILogicChain logicChain = defaultChainFactory.openLogicChain(10001L);
        Integer awardId = logicChain.logic("user001", 10001L);
        log.info("测试结果:{}",awardId);
    }

    @Test
    public void test_LogicChain_rule_weight(){
        //通过反射 mock 规则中的值
        ReflectionTestUtils.setField(ruleWeightLogicChain,"userScore",4900L);
        ILogicChain logicChain = defaultChainFactory.openLogicChain(10001L);
        Integer awardId = logicChain.logic("hzq", 10001L);
        log.info("测试结果:{}",awardId);
    }
    @Test
    public void test_LogicChain_rule_default(){
        ILogicChain logicChain = defaultChainFactory.openLogicChain(10001L);
        Integer awardId = logicChain.logic("hzq", 10001L);
        log.info("测试结果:{}",awardId);
    }





}
