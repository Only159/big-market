package cn.hzq.infrastructure.event;

import cn.hzq.types.event.BaseEvent;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 黄照权
 * @Date 2024/5/4
 * @Description 消息发送
 **/
@Slf4j
@Component
public class EventPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送MQ消息
     * @param topic 消息主题
     * @param eventMessage 消息体
     */

    public void publish(String topic, BaseEvent.EventMessage<?> eventMessage) {
        try {
            String messageJson = JSON.toJSONString(eventMessage);
            rabbitTemplate.convertAndSend(topic, messageJson);
            log.info("发送MQ消息，topic：{} message：{}", topic, eventMessage);
        } catch (Exception e) {
            log.error("发送MQ消息失败，topic：{} message：{}", topic, eventMessage, e);
            throw e;
        }
    }

}
