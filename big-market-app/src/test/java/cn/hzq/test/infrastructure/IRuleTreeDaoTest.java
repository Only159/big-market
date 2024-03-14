package cn.hzq.test.infrastructure;

import cn.hzq.infrastructure.persistent.dao.IRuleTreeDao;
import cn.hzq.infrastructure.persistent.po.RuleTree;
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
 * @Date 2024/3/14
 * @Description 规则树持久化单元测试
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class IRuleTreeDaoTest {
    @Resource
    private IRuleTreeDao ruleTreeDao;

    @Test
    public  void test_queryRuleTreeList(){
        List<RuleTree> ruleTrees = ruleTreeDao.queryRuleTreeList();
        log.info("测试结果：{}", JSON.toJSONString(ruleTrees));
    }
}
