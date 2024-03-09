package cn.hzq.test.infrastructure;

import cn.hzq.infrastructure.persistent.dao.IStrategyAwardDao;
import cn.hzq.infrastructure.persistent.po.StrategyAward;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/3/10
 * @Description 策略奖品明细单元测试
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class IStrategyAwardDaoTest {
    @Resource
    private IStrategyAwardDao strategyAwardDao;

    @Test
    public void test_queryStrategyAwardList(){
        List<StrategyAward> strategyAwards = strategyAwardDao.queryStrategyAwardList();
        log.info("测试结果：{}", JSON.toJSONString(strategyAwards));
    };

}
