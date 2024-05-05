package cn.hzq.domain.award.event;

import cn.hzq.types.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 奖品发放MQ消息实体对象
 **/
@Component
public class SendAwardMessageEvent extends BaseEvent<SendAwardMessageEvent.SendAwardMessage> {
    @Value("${spring.rabbitmq.topic.send_award}")
    private String topic;

    @Override
    public EventMessage<SendAwardMessage> buildEventMessage(SendAwardMessage data) {
        return EventMessage.<SendAwardMessage>builder()
                .id(RandomStringUtils.randomNumeric(11))
                .timestamp(new Date())
                .data(data)
                .build();
    }

    @Override
    public String topic() {
        return topic;
    }

    @Data
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class SendAwardMessage {
        /**
         * 用户Id
         */
        private String userId;
        /**
         * 奖品Id
         */
        private Integer awardId;
        /**
         * 奖品名称（标题）
         */
        private String awardTitle;
    }
}
