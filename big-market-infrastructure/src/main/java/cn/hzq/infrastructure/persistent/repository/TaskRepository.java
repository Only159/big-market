package cn.hzq.infrastructure.persistent.repository;

import cn.hzq.domain.task.model.entity.TaskEntity;
import cn.hzq.domain.task.repository.ITaskRepository;
import cn.hzq.infrastructure.event.EventPublisher;
import cn.hzq.infrastructure.persistent.dao.ITaskDao;
import cn.hzq.infrastructure.persistent.po.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/6
 * @Description 任务仓储服务接口实现
 **/
@Repository
@Slf4j
public class TaskRepository implements ITaskRepository {
    @Resource
    private ITaskDao taskDao;
    @Resource
    private EventPublisher eventPublisher;

    @Override
    public List<TaskEntity> queryNoSendMessageTaskList() {
        List<Task> tasks = taskDao.queryNoSendMessageTaskList();
        List<TaskEntity> taskEntityList = new ArrayList<>();
        for (Task task : tasks) {
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setUserId(task.getUserId());
            taskEntity.setMessage(task.getMessage());
            taskEntity.setMessageId(task.getMessageId());
            taskEntity.setTopic(task.getTopic());
            taskEntityList.add(taskEntity);
        }
        return taskEntityList;
    }

    @Override
    public void sendMessage(TaskEntity taskEntity) {
        eventPublisher.publish(taskEntity.getTopic(), taskEntity.getMessage());
    }

    @Override
    public void updateTaskSendMessageCompleted(TaskEntity taskEntity) {
        Task taskReq = new Task();
        taskReq.setUserId(taskEntity.getUserId());
        taskReq.setMessageId(taskEntity.getMessageId());
        taskDao.updateTaskSendMessageCompleted(taskReq);
    }

    @Override
    public void updateTaskSendMessageFail(TaskEntity taskEntity) {
        Task taskReq = new Task();
        taskReq.setUserId(taskEntity.getUserId());
        taskReq.setMessageId(taskEntity.getMessageId());
        taskDao.updateTaskSendMessageFail(taskReq);
    }
}
