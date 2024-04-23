package cn.hzq.test.infrastructure;

import cn.hzq.infrastructure.persistent.dao.IRaffleActivityOrderDao;
import cn.hzq.infrastructure.persistent.po.RaffleActivityOrder;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/4/23
 * @Description 抽奖活动订单DAO
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class IRaffleActivityOrderDaoTest {
    @Resource
    private IRaffleActivityOrderDao raffleActivityOrderDao;
    @Test
    public void test_insert(){
        RaffleActivityOrder raffleActivityOrder = new RaffleActivityOrder();
        raffleActivityOrder.setUserId("hzq");
        raffleActivityOrder.setActivityId(100401L);
        raffleActivityOrder.setActivityName("测试活动-hzq");
        raffleActivityOrder.setStrategyId(100006L);
        raffleActivityOrder.setOrderId(RandomStringUtils.randomNumeric(12));
        raffleActivityOrder.setOrderTime(new Date());
        raffleActivityOrder.setState("not_used");
        //插入数据
        raffleActivityOrderDao.insert(raffleActivityOrder);
    }

    @Test
    public void test_queryRaffleActivityOrderByUserId() {
        String userId = "hzq";
        List<RaffleActivityOrder> raffleActivityOrders =
                raffleActivityOrderDao.queryRaffleActivityOrderByUserId(userId);
        log.info("测试结果：{}" , JSON.toJSONString(raffleActivityOrders));
    }
}
