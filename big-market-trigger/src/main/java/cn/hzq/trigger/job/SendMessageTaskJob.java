package cn.hzq.trigger.job;

import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import cn.hzq.domain.task.model.entity.TaskEntity;
import cn.hzq.domain.task.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 发送MQ消息队列任务
 **/
@Slf4j
@Component()
public class SendMessageTaskJob {
    @Resource
    private ITaskService taskService;
    @Resource
    private ThreadPoolExecutor executor;
    @Resource
    private IDBRouterStrategy dbRouter;

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec_db01() {
        try {
            // 分库路由
            dbRouter.setDBKey(1);
            dbRouter.setTBKey(0);
            // 获取未发送或者发送失败的消息任务
            List<TaskEntity> taskEntityList = taskService.queryNoSendMessageTaskList();
            if (taskEntityList.isEmpty()) return;
            //发送MQ消息
            for (TaskEntity taskEntity : taskEntityList) {
                try {
                    taskService.sendMessage(taskEntity);
                    taskService.updateTaskSendMessageCompleted(taskEntity);
                    log.info("定时任务，重新发送MQ消息 userId: {} topic: {}", taskEntity.getUserId(), taskEntity.getTopic());
                } catch (Exception e) {
                    log.error("定时任务，发送MQ消息失败 userId: {} topic: {}", taskEntity.getUserId(), taskEntity.getTopic(), e);
                    taskService.updateTaskSendMessageFail(taskEntity);
                }
            }
        } catch (
                Exception e) {
            log.error("定时任务，扫描MQ任务表发送MQ消息失败", e);
        } finally {
            dbRouter.clear();
        }
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec_db02() {
        try {
            // 分库路由
            dbRouter.setDBKey(1);
            dbRouter.setTBKey(0);
            // 获取未发送或者发送失败的消息任务
            List<TaskEntity> taskEntityList = taskService.queryNoSendMessageTaskList();
            if (taskEntityList.isEmpty()) return;
            //发送MQ消息
            for (TaskEntity taskEntity : taskEntityList) {
                try {
                    taskService.sendMessage(taskEntity);
                    taskService.updateTaskSendMessageCompleted(taskEntity);
                    log.info("定时任务，重新发送MQ消息 userId: {} topic: {}", taskEntity.getUserId(), taskEntity.getTopic());
                } catch (Exception e) {
                    log.error("定时任务，发送MQ消息失败 userId: {} topic: {}", taskEntity.getUserId(), taskEntity.getTopic(), e);
                    taskService.updateTaskSendMessageFail(taskEntity);
                }
            }
        } catch (
                Exception e) {
            log.error("定时任务，扫描MQ任务表发送MQ消息失败", e);
        } finally {
            dbRouter.clear();
        }
    }
}
