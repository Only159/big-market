package cn.hzq.domain.award.service;

import cn.hzq.domain.award.event.SendAwardMessageEvent;
import cn.hzq.domain.award.model.aggregate.UserAwardRecordAggregate;
import cn.hzq.domain.award.model.entity.DistributeAwardEntity;
import cn.hzq.domain.award.model.entity.TaskEntity;
import cn.hzq.domain.award.model.entity.UserAwardRecordEntity;
import cn.hzq.domain.award.model.valobj.TaskStateVO;
import cn.hzq.domain.award.repository.IAwardRepository;
import cn.hzq.domain.award.service.distribute.IDistributeAward;
import cn.hzq.types.event.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 奖品服务
 **/
@Service
@Slf4j
public class AwardService implements IAwardService {

    private final IAwardRepository awardRepository;
    private final SendAwardMessageEvent sendAwardMessageEvent;
    private final Map<String, IDistributeAward> distributeAwardMap;

    public AwardService(IAwardRepository awardRepository, SendAwardMessageEvent sendAwardMessageEvent, Map<String, IDistributeAward> distributeAwardMap) {
        this.awardRepository = awardRepository;
        this.sendAwardMessageEvent = sendAwardMessageEvent;
        this.distributeAwardMap = distributeAwardMap;
    }

    @Override
    public void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity) {
        // 构建消息对象
        SendAwardMessageEvent.SendAwardMessage sendAwardMessage = new SendAwardMessageEvent.SendAwardMessage();
        sendAwardMessage.setUserId(userAwardRecordEntity.getUserId());
        sendAwardMessage.setOrderId(userAwardRecordEntity.getOrderId());
        sendAwardMessage.setAwardId(userAwardRecordEntity.getAwardId());
        sendAwardMessage.setAwardTitle(userAwardRecordEntity.getAwardTitle());
        sendAwardMessage.setAwardConfig(userAwardRecordEntity.getAwardConfig());

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

    @Override
    public void distributeAward(DistributeAwardEntity distributeAwardEntity) {
        // 奖品key
        String awardKey = awardRepository.queryAwardKey(distributeAwardEntity.getAwardId());
        if (null == awardKey) {
            log.error("分发奖品，奖品Id不存在，查询不到awardKey，awardId:{}", distributeAwardEntity.getAwardId());
            return;
        }
        IDistributeAward distributeAward = distributeAwardMap.get(awardKey);
        if (null == distributeAward) {
            log.error("分发奖品，对应服务不存在，awardKey：{}", awardKey);
            // TODO 后续完善全部奖品后开启异常
            //throw new RuntimeException("分发奖品,奖品" + awardKey + "对应服务不存在");
            return;
        }
        distributeAward.giveOutPrizes(distributeAwardEntity);

    }
}
