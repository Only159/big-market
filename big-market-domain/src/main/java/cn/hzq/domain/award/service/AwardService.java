package cn.hzq.domain.award.service;

import cn.hzq.domain.award.event.SendAwardMessageEvent;
import cn.hzq.domain.award.model.aggregate.UserAwardRecordAggregate;
import cn.hzq.domain.award.model.entity.TaskEntity;
import cn.hzq.domain.award.model.entity.UserAwardRecordEntity;
import cn.hzq.domain.award.model.valobj.TaskStateVO;
import cn.hzq.domain.award.repository.IAwardRepository;
import cn.hzq.types.event.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description
 **/
@Service
@Slf4j
public class AwardService implements IAwardService {
    @Resource
    private IAwardRepository awardRepository;
    @Resource
    private SendAwardMessageEvent sendAwardMessageEvent;

    @Override
    public void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity) {
        // 构建消息对象
        SendAwardMessageEvent.SendAwardMessage sendAwardMessage = new SendAwardMessageEvent.SendAwardMessage();
        sendAwardMessage.setUserId(userAwardRecordEntity.getUserId());
        sendAwardMessage.setAwardId(userAwardRecordEntity.getAwardId());
        sendAwardMessage.setAwardTitle(userAwardRecordEntity.getAwardTitle());

        BaseEvent.EventMessage<SendAwardMessageEvent.SendAwardMessage> sendAwardMessageEventMessage =
                sendAwardMessageEvent.buildEventMessage(sendAwardMessage);

        // 构建任务对象
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setUserId(userAwardRecordEntity.getUserId());
        taskEntity.setTopic(sendAwardMessageEvent.topic());
        taskEntity.setMessageId(sendAwardMessageEventMessage.getId());
        taskEntity.setMessage(sendAwardMessageEventMessage);
        taskEntity.setState(TaskStateVO.create);

        // 构建聚合对象
        UserAwardRecordAggregate userAwardRecordAggregate = UserAwardRecordAggregate.builder()
                .userAwardRecordEntity(userAwardRecordEntity)
                .taskEntity(taskEntity)
                .build();

        // 存储聚合对象，一个事务下，用户的中奖记录
        awardRepository.saveUserAwardRecord(userAwardRecordAggregate);
    }
}
