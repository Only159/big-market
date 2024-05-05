package cn.hzq.domain.task.repository;

import cn.hzq.domain.task.model.entity.TaskEntity;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/6
 * @Description 任务服务仓储接口
 **/
public interface ITaskRepository {
    /**
     * 获取任务未发送列表
     *
     * @return 任务实体对象列表
     */
    List<TaskEntity> queryNoSendMessageTaskList();

    /**
     * 发送消息
     *
     * @param taskEntity 任务实体
     */
    void sendMessage(TaskEntity taskEntity);

    /**
     * 更新任务记录为完成
     *
     * @param taskEntity 任务实体
     */
    void updateTaskSendMessageCompleted(TaskEntity taskEntity);

    /**
     * 更新任务记录为失败
     *
     * @param taskEntity 任务实体
     */
    void updateTaskSendMessageFail(TaskEntity taskEntity);
}
