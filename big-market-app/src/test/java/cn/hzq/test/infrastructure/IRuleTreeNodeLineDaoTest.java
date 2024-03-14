package cn.hzq.test.infrastructure;

import cn.hzq.infrastructure.persistent.dao.IRuleTreeNodeLineDao;
import cn.hzq.infrastructure.persistent.po.RuleTreeNodeLine;
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
 * @Description 规则树节点线[from -> to] 持久化单元测试
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class IRuleTreeNodeLineDaoTest {
    @Resource
    private IRuleTreeNodeLineDao ruleTreeNodeLineDao;

    @Test
    public void test_queryRuleTreeNodeLineList() {
        List<RuleTreeNodeLine> ruleTreeNodeLines = ruleTreeNodeLineDao.queryRuleTreeNodeLineList();
        log.info("测试结果：{}", JSON.toJSONString(ruleTreeNodeLines));
    }
}
