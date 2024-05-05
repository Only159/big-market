package cn.hzq.test.domain.award;

import cn.hzq.domain.award.model.entity.UserAwardRecordEntity;
import cn.hzq.domain.award.model.valobj.AwardStateVO;
import cn.hzq.domain.award.service.IAwardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 黄照权
 * @Date 2024/5/6
 * @Description 奖品发放接口测试
 **/

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AwardServiceTest {

    @Resource
    private IAwardService awardService;

    @Test
    public void test_savaUserAwardOrder() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            UserAwardRecordEntity userAwardRecordEntity = new UserAwardRecordEntity();
            userAwardRecordEntity.setUserId("hzq");
            userAwardRecordEntity.setActivityId(100301L);
            userAwardRecordEntity.setStrategyId(100006L);
            userAwardRecordEntity.setOrderId(RandomStringUtils.randomNumeric(12));
            userAwardRecordEntity.setAwardId(101);
            userAwardRecordEntity.setAwardTitle("OpenAI 增加使用次数");
            userAwardRecordEntity.setAwardTime(new Date());
            userAwardRecordEntity.setAwardState(AwardStateVO.create);
            awardService.saveUserAwardRecord(userAwardRecordEntity);
            Thread.sleep(500);
        }
    }
}
