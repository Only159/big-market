package cn.hzq.domain.rebate.model.entity;

import cn.hzq.domain.rebate.event.SendRebateMessageEvent;
import cn.hzq.domain.rebate.model.valobj.TaskStateVO;
import cn.hzq.types.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/5/10
 * @Description 任务实体对象
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 消息主题
     */
    private String topic;
    /**
     * 消息编号
     */
    private String messageId;
    /**
     * 消息主体
     */
    private BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage> message;
    /**
     * 任务状态；create-创建、completed-完成、fail-失败
     */
    private TaskStateVO state;

}
