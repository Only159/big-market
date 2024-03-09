package cn.hzq.test.infrastructure;

import cn.hzq.infrastructure.persistent.dao.IStrategyRuleDao;
import cn.hzq.infrastructure.persistent.po.StrategyRule;
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
 * @Description
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class IStrategyRuleDaoTest {

    @Resource
    private IStrategyRuleDao strategyRuleDao;

    @Test
    public void test_queryStrategyRuleList(){
        List<StrategyRule> strategyRules = strategyRuleDao.queryStrategyRuleList();
        log.info("测试结果：{}", JSON.toJSONString(strategyRules));
    }

}
