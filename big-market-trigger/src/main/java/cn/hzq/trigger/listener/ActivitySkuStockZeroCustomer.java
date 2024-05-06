package cn.hzq.trigger.listener;

import cn.hzq.domain.activity.service.IRaffleActivitySkuStockService;
import cn.hzq.types.event.BaseEvent;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 活动sku 库存耗尽
 **/
@Slf4j
@Component
public class ActivitySkuStockZeroCustomer {
    @Value("${spring.rabbitmq.topic.activity_sku_stock_zero}")
    private String topic;
    @Resource
    private IRaffleActivitySkuStockService skuStock;

    @RabbitListener(queuesToDeclare = @Queue("activity_sku_stock_zero"))
    public void listener(String message) {
        try {
            log.info("监听活动sku库存耗尽为0的消息 topic:{} message:{}", topic, message);
            // 转换对象
            BaseEvent.EventMessage<Long> eventMessage = JSON.parseObject(message, new TypeReference<BaseEvent.EventMessage<Long>>() {
            }.getType());
            Long sku = eventMessage.getData();
            // 更新库存
            skuStock.clearActivitySkuStock(sku);
            // 清空队列 【库存为0，已经不需要延迟队列记录数据了】 【清空所有队列】
            //TODO 指定处理
            skuStock.clearQueueValue();
        } catch (Exception e) {
            log.error("监听活动sku库存消耗为0消息，消费失败 topic:{}，message:{}", topic, message);
        }
    }
}
