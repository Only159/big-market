package cn.hzq.domain.award.model.entity;

import cn.hzq.domain.award.event.SendAwardMessageEvent;
import cn.hzq.domain.award.model.valobj.TaskStateVO;
import cn.hzq.types.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 任务实体对象
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    /**
     * 用户Id
     */
    private String userId;

    /**
     * 消息主题
     */
    private String topic;
    /**
     * 消息Id
     */
    private String messageId;
    /**
     * 消息主体
     */
    private BaseEvent.EventMessage<SendAwardMessageEvent.SendAwardMessage> message;
    /**
     * 任务状态；create-创建、completed-完成、fail-失败
     */
    private TaskStateVO state;
}
