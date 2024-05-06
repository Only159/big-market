package cn.hzq.test.trigger;

import cn.hzq.domain.activity.service.armory.IActivityArmory;
import cn.hzq.domain.strategy.service.armory.IStrategyArmory;
import cn.hzq.trigger.api.IRaffleStrategyService;
import cn.hzq.trigger.api.dto.RaffleAwardListRequestDTO;
import cn.hzq.trigger.api.dto.RaffleAwardListResponseDTO;
import cn.hzq.types.model.Response;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/6
 * @Description 营销抽奖服务测试
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleStrategyControllerTest {
    @Resource
    private IRaffleStrategyService raffleStrategyService;
    @Resource
    private IActivityArmory activityArmory;
    @Resource
    private IStrategyArmory strategyArmory;

    @Before
    public void setUp() {
        log.info("装配活动：{}", strategyArmory.assembleLotteryStrategyByActivityId(100301L));
    }

    @Test
    public void test_queryRaffleAwardList() {
        RaffleAwardListRequestDTO request = new RaffleAwardListRequestDTO();
        request.setActivityId(100301L);
        request.setUserId("hzq");
        Response<List<RaffleAwardListResponseDTO>> response = raffleStrategyService.queryRaffleAwardList(request);
        log.info("请求参数：{}", JSON.toJSONString(request));
        log.info("响应结果：{}", JSON.toJSONString(response));

    }
}
