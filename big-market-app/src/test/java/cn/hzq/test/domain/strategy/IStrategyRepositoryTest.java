package cn.hzq.test.domain.strategy;

import cn.hzq.domain.strategy.model.valobj.RuleTreeVO;
import cn.hzq.domain.strategy.repository.IStrategyRepository;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/3/14
 * @Description 仓储接口测试
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class IStrategyRepositoryTest {
    @Resource
    private IStrategyRepository repository;

    @Test
    public void test_queryRuleTreeVOByTreeId(){
        RuleTreeVO ruleTreeVO = repository.queryRuleTreeVOByTreeId("tree_lock");
        log.info("测试结果：{}", JSON.toJSONString(ruleTreeVO));
    }
}
