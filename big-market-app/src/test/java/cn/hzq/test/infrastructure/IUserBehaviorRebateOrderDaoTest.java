package cn.hzq.test.infrastructure;

import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import cn.hzq.infrastructure.persistent.dao.IUserBehaviorRebateOrderDao;
import cn.hzq.infrastructure.persistent.po.UserBehaviorRebateOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/5/11
 * @Description
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserBehaviorRebateOrderDaoTest {
    @Resource
    private IUserBehaviorRebateOrderDao userBehaviorRebateOrderDao;
    @Resource
    private IDBRouterStrategy idbRouterStrategy;

    @Test
    public void test_insert() {


        UserBehaviorRebateOrder userBehaviorRebateOrder = new UserBehaviorRebateOrder();
        userBehaviorRebateOrder.setUserId("hzq");
        userBehaviorRebateOrder.setOrderId("123456789111");
        userBehaviorRebateOrder.setRebateType("sku");
        userBehaviorRebateOrder.setBehaviorType("sign");
        userBehaviorRebateOrder.setRebateConfig("9011");
        userBehaviorRebateOrder.setRebateDesc("测试");
        userBehaviorRebateOrder.setBizId("hzq_sign_240511");
        idbRouterStrategy.doRouter("hzq");
        userBehaviorRebateOrderDao.insert(userBehaviorRebateOrder);

    }
}
