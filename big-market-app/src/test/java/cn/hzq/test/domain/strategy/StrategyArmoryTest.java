package cn.hzq.test.domain.strategy;

import cn.hzq.domain.strategy.service.armory.IStrategyArmory;
import cn.hzq.domain.strategy.service.armory.IStrategyDispatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/3/11
 * @Description 策略领域测试
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyArmoryTest {

    @Resource
    private IStrategyArmory strategyArmory;
    @Resource
    private IStrategyDispatch strategyDispatch;

    /**
     * 策略ID；10001L、10002L 装配的时候创建策略表写入到 Redis Map 中
     */
    @Test
    public void test_strategyArmory() {
        boolean success = strategyArmory.assembleLotteryStrategy(100006L);
        log.info("测试结果：{}", success);
    }

    /**
     * 从装配的策略中随机获取奖品ID值
     */
    @Test
    public void test_getAssembleRandomVal() {
        log.info("测试结果: {} - 奖品id值", strategyDispatch.getRandomAwardId(100006L));

    }
    @Test
    public void test_getAssembleRandomVal_ruleWeightValue() {
        log.info("测试结果: {} - 4000策略奖品id值", strategyDispatch.getRandomAwardId(10001L,"4000"));
        log.info("测试结果: {} - 5000策略奖品id值", strategyDispatch.getRandomAwardId(10001L,"5000"));
        log.info("测试结果: {} - 6000策略奖品id值", strategyDispatch.getRandomAwardId(10001L,"6000"));
    }

}
