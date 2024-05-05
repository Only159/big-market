package cn.hzq.domain.task.service;

import cn.hzq.domain.task.model.entity.TaskEntity;
import cn.hzq.domain.task.repository.ITaskRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/6
 * @Description 消息任务服务接口
 **/
@Service
public class TaskService implements ITaskService {
    @Resource
    private ITaskRepository taskRepository;
    @Override
    public List<TaskEntity> queryNoSendMessageTaskList() {
        return taskRepository.queryNoSendMessageTaskList();
    }

    @Override
    public void sendMessage(TaskEntity taskEntity) {
        taskRepository.sendMessage(taskEntity);
    }

    @Override
    public void updateTaskSendMessageCompleted(TaskEntity task) {
        taskRepository.updateTaskSendMessageCompleted(task);
    }

    @Override
    public void updateTaskSendMessageFail(TaskEntity task) {
        taskRepository.updateTaskSendMessageFail(task);
    }
}
