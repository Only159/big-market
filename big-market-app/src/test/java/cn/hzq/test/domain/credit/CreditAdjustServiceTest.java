package cn.hzq.test.domain.credit;

import cn.hzq.domain.credit.model.entity.TradeEntity;
import cn.hzq.domain.credit.model.valobj.TradeNameVO;
import cn.hzq.domain.credit.model.valobj.TradeTypeVO;
import cn.hzq.domain.credit.service.ICreditAdjustService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-12
 * @Description: 积分服务测试类
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditAdjustServiceTest {
    @Resource
    private ICreditAdjustService creditAdjustService;

    @Test
    public void testCreateOrder() {
        //模拟对象
        TradeEntity tradeEntity = new TradeEntity();
        tradeEntity.setUserId("hzq");
        tradeEntity.setTradeName(TradeNameVO.REBATE);
        tradeEntity.setTradeType(TradeTypeVO.FORWARD);
        tradeEntity.setAmount(new BigDecimal("-10.19"));
        tradeEntity.setOutBusinessNo("10000990993");
        //测试
        creditAdjustService.createOrder(tradeEntity);

    }

}
