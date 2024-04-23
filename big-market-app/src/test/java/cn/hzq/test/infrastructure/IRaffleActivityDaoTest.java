package cn.hzq.test.infrastructure;

import cn.hzq.infrastructure.persistent.dao.IRaffleActivityDao;
import cn.hzq.infrastructure.persistent.po.RaffleActivity;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/4/23
 * @Description 抽奖活动表单元测试
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class IRaffleActivityDaoTest {
    @Resource
    private IRaffleActivityDao raffleActivityDao;

    @Test
    public void test_queryRaffleActivityByActivityId() {
        RaffleActivity raffleActivity =
                raffleActivityDao.queryRaffleActivityByActivityId(100301L);
        log.info("测试结果：{}", JSON.toJSONString(raffleActivity));
    }
}
