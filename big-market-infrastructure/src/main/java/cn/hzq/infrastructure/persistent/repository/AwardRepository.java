package cn.hzq.infrastructure.persistent.repository;

import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import cn.hzq.domain.award.model.aggregate.UserAwardRecordAggregate;
import cn.hzq.domain.award.model.entity.TaskEntity;
import cn.hzq.domain.award.model.entity.UserAwardRecordEntity;
import cn.hzq.domain.award.repository.IAwardRepository;
import cn.hzq.infrastructure.event.EventPublisher;
import cn.hzq.infrastructure.persistent.dao.ITaskDao;
import cn.hzq.infrastructure.persistent.dao.IUserAwardRecordDao;
import cn.hzq.infrastructure.persistent.po.Task;
import cn.hzq.infrastructure.persistent.po.UserAwardRecord;
import cn.hzq.types.enums.ResponseCode;
import cn.hzq.types.exception.AppException;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 奖品仓储服务实现
 **/
@Repository
@Slf4j
public class AwardRepository implements IAwardRepository {
    @Resource
    private ITaskDao taskDao;
    @Resource
    private IUserAwardRecordDao userAwardRecordDao;
    @Resource
    private IDBRouterStrategy dbRouter;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource
    private EventPublisher eventPublisher;

    @Override
    public void saveUserAwardRecord(UserAwardRecordAggregate userAwardRecordAggregate) {
        //获取数据
        UserAwardRecordEntity userAwardRecordEntity = userAwardRecordAggregate.getUserAwardRecordEntity();
        TaskEntity taskEntity = userAwardRecordAggregate.getTaskEntity();
        String userId = userAwardRecordEntity.getUserId();
        Long activityId = userAwardRecordEntity.getActivityId();
        Integer awardId = userAwardRecordEntity.getAwardId();

        UserAwardRecord userAwardRecord = new UserAwardRecord();
        userAwardRecord.setUserId(userAwardRecordEntity.getUserId());
        userAwardRecord.setActivityId(userAwardRecordEntity.getActivityId());
        userAwardRecord.setStrategyId(userAwardRecordEntity.getStrategyId());
        userAwardRecord.setOrderId(userAwardRecordEntity.getOrderId());
        userAwardRecord.setAwardId(userAwardRecordEntity.getAwardId());
        userAwardRecord.setAwardTitle(userAwardRecordEntity.getAwardTitle());
        userAwardRecord.setAwardTime(userAwardRecordEntity.getAwardTime());
        userAwardRecord.setAwardState(userAwardRecordEntity.getAwardState().getCode());

        Task task = new Task();
        task.setUserId(taskEntity.getUserId());
        task.setTopic(taskEntity.getTopic());
        task.setMessageId(taskEntity.getMessageId());
        task.setMessage(JSON.toJSONString(taskEntity.getMessage()));
        task.setState(taskEntity.getState().getCode());

        try {
            dbRouter.doRouter(userId);
            transactionTemplate.execute(status -> {
                try {
                    //写入记录
                    userAwardRecordDao.insert(userAwardRecord);
                    //写入任务
                    taskDao.insert(task);
                    return 1;
                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    log.error("写入中奖记录。唯一索引冲。userId:{} activity:{}", userId, activityId, e);
                    throw new AppException(ResponseCode.INDEX_DUP.getCode(), ResponseCode.INDEX_DUP.getInfo(), e);
                }
            });
        } finally {
            dbRouter.clear();
        }

        try {
            // 发送消息【在事务外执行，如果失败还有任务补偿】
            eventPublisher.publish(task.getTopic(), task.getMessage());
            //更新数据库记录
            taskDao.updateTaskSendMessageCompleted(task);
        } catch (Exception e) {
            log.error("写入中奖记录。发送MQ消息失败。userId:{} activityId:{}", userId, activityId, e);
            taskDao.updateTaskSendMessageFail(task);
        }

    }
}
