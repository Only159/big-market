package cn.hzq.test.domain;

import cn.hzq.domain.strategy.service.armory.IStrategyArmory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/3/11
 * @Description
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyArmoryTest {

    @Resource
    private IStrategyArmory strategyArmory;

    @Test
    public void  test_strategyArmory(){
        strategyArmory.assembleLotteryStrategy(10001L);
    }

    @Test
    public void  test_getAssembleRandomVal(){
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
        log.info("测试结果: {} - 奖品id值",strategyArmory.getRandomAwardId(10001L));
    }

}
