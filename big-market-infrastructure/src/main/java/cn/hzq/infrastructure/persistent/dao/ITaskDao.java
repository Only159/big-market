package cn.hzq.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.hzq.infrastructure.persistent.po.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 任务表 发放MQ
 **/
@Mapper
public interface ITaskDao {
    /**
     * 插入任务记录
     * @param task 任务实体
     */
    void insert(Task task);

    /**
     * 更新任务记录为完成
     * @param task 任务实体
     */
    @DBRouter
    void updateTaskSendMessageCompleted(Task task);

    /**
     * 更新任务记录为失败
     * @param task 任务实体
     */
    @DBRouter
    void updateTaskSendMessageFail(Task task);

    /**
     * 获取未发送的任务对象
     * @return 任务实体对象列表
     */
    List<Task> queryNoSendMessageTaskList();
}
