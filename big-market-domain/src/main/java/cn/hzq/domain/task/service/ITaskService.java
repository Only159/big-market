package cn.hzq.domain.task.service;



import cn.hzq.domain.task.model.entity.TaskEntity;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 消息任务接口服务
 **/
public interface ITaskService {
    /**
     * 获取任务未发送列表
     * @return 任务实体对象列表
     */
    List<TaskEntity> queryNoSendMessageTaskList();
    /**
     * 发送消息
     * @param taskEntity 任务实体
     */
    void sendMessage(TaskEntity taskEntity);
    /**
     * 更新任务记录为完成
     * @param task 任务实体
     */
    void updateTaskSendMessageCompleted(TaskEntity task);

    /**
     * 更新任务记录为失败
     * @param task 任务实体
     */
    void updateTaskSendMessageFail(TaskEntity task);
}
