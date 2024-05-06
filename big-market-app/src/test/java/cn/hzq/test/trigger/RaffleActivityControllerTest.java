package cn.hzq.test.trigger;

import cn.hzq.trigger.api.IRaffleActivityService;
import cn.hzq.trigger.api.dto.ActivityDrawRequestDTO;
import cn.hzq.trigger.api.dto.ActivityDrawResponseDTO;
import cn.hzq.types.model.Response;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/5/6
 * @Description 营销活动服务测试
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleActivityControllerTest {
    @Resource
    private IRaffleActivityService raffleActivityService;

    @Test
    public void test_armory() {
        Response<Boolean> success = raffleActivityService.armory(100301L);
        log.info("活动装配测试结果:{}", success);
    }

    @Test
    public void test_draw() {
        ActivityDrawRequestDTO activityDrawRequestDTO = new ActivityDrawRequestDTO();
        activityDrawRequestDTO.setActivityId(100301L);
        activityDrawRequestDTO.setUserId("hzq");
        Response<ActivityDrawResponseDTO> draw = raffleActivityService.draw(activityDrawRequestDTO);
        log.info("请求参数：{}", JSON.toJSONString(activityDrawRequestDTO));
        log.info("测试结果：{}", JSON.toJSONString(draw));
    }

}
