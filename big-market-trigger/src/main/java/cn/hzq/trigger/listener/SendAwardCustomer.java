package cn.hzq.trigger.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 黄照权
 * @Date 2024/5/6
 * @Description
 **/
@Slf4j
@Component
public class SendAwardCustomer {
    @Value("${spring.rabbitmq.topic.send_award}")
    private String topic;

    @RabbitListener(queuesToDeclare = @Queue(value = "${spring.rabbitmq.topic.send_award}"))
    public void listener(String message) {
        try {
            log.info("监听用奖品发送消息 topic:{} message:{}", topic, message);
            //TODO 获取消息之后未处理
        } catch (Exception e) {
            log.error("监听用奖品发送消息,消费失败。 topic:{} message:{}", topic, message);
            throw e;
        }
    }
}
