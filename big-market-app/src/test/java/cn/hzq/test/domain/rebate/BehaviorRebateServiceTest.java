package cn.hzq.test.domain.rebate;

import cn.hzq.domain.rebate.model.entity.BehaviorEntity;
import cn.hzq.domain.rebate.model.valobj.BehaviorTypeVO;
import cn.hzq.domain.rebate.service.IBehaviorRebateService;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/10
 * @Description 行为返利接口服务测试类
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BehaviorRebateServiceTest {
    @Resource
    private IBehaviorRebateService behaviorRebateService;

    @Test
    public void test_createOrder() {
        BehaviorEntity behaviorEntity = new BehaviorEntity();
        behaviorEntity.setUserId("hzq");
        behaviorEntity.setBehaviorTypeVO(BehaviorTypeVO.SIGN);
        behaviorEntity.setOutBusinessNo("20240505");

        List<String> orderIds = behaviorRebateService.createOrder(behaviorEntity);
        log.info("请求参数:{}", JSON.toJSONString(behaviorEntity));
        log.info("测试结果:{}", JSON.toJSONString(orderIds));

    }

}
