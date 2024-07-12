package cn.hzq.trigger.listener;

import cn.hzq.domain.activity.model.entity.SkuRechargeEntity;
import cn.hzq.domain.activity.service.IRaffleActivityAccountQuotaService;
import cn.hzq.domain.credit.model.entity.TradeEntity;
import cn.hzq.domain.credit.model.valobj.TradeNameVO;
import cn.hzq.domain.credit.model.valobj.TradeTypeVO;
import cn.hzq.domain.credit.service.ICreditAdjustService;
import cn.hzq.domain.rebate.event.SendRebateMessageEvent;
import cn.hzq.domain.rebate.model.valobj.RebateTypeVO;
import cn.hzq.types.enums.ResponseCode;
import cn.hzq.types.event.BaseEvent;
import cn.hzq.types.exception.AppException;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author 黄照权
 * @Date 2024/5/11
 * @Description 监听行为返利消息
 **/
@Slf4j
@Component
public class RebateMessageCustomer {
    @Value("${spring.rabbitmq.topic.send_rebate}")
    private String topic;
    @Resource
    private IRaffleActivityAccountQuotaService raffleActivityAccountQuotaService;
    @Resource
    private ICreditAdjustService creditAdjustService;

    @RabbitListener(queuesToDeclare = @Queue(value = "${spring.rabbitmq.topic.send_rebate}"))
    public void listener(String message) {
        try {
            log.info("监听用户行为返利消息 :topic：{},message:{}", topic, message);
            // 1.转换消息
            BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage> eventMessage = JSON.parseObject(message, new TypeReference<BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage>>() {
            }.getType());
            SendRebateMessageEvent.RebateMessage rebateMessage = eventMessage.getData();

            // 2.入账奖励
            switch (rebateMessage.getRebateType()) {
                case "sku":
                    // 入账奖励对象构建
                    SkuRechargeEntity skuRechargeEntity = new SkuRechargeEntity();
                    skuRechargeEntity.setUserId(rebateMessage.getUserId());
                    skuRechargeEntity.setSku(Long.valueOf(rebateMessage.getRebateConfig()));
                    skuRechargeEntity.setOutBusinessNo(rebateMessage.getBizId());
                    // 写入账户
                    raffleActivityAccountQuotaService.createOrder(skuRechargeEntity);
                    break;
                case "integral":
                    // 积分奖励对象构建
                    TradeEntity tradeEntity = new TradeEntity();
                    tradeEntity.setUserId(rebateMessage.getUserId());
                    tradeEntity.setTradeName(TradeNameVO.REBATE);
                    tradeEntity.setTradeType(TradeTypeVO.FORWARD);
                    tradeEntity.setAmount(new BigDecimal(rebateMessage.getRebateConfig()));
                    tradeEntity.setOutBusinessNo(rebateMessage.getBizId());
                    creditAdjustService.createOrder(tradeEntity);
                    break;
                    
            }
        } catch (AppException e) {
            if (ResponseCode.INDEX_DUP.getCode().equals(e.getCode())) {
                log.warn("监听用户行为返利消息，消费重复 topic: {} message: {}", topic, message, e);
                return;
            }
            throw e;
        } catch (Exception e) {
            log.error("监听用户行为返利消息，消费失败 topic: {} message: {}", topic, message, e);
            throw e;
        }
    }
}
