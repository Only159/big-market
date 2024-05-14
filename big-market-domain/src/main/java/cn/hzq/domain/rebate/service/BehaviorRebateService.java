package cn.hzq.domain.rebate.service;

import cn.hzq.domain.rebate.event.SendRebateMessageEvent;
import cn.hzq.domain.rebate.model.aggregate.BehaviorRebateAggregate;
import cn.hzq.domain.rebate.model.entity.BehaviorEntity;
import cn.hzq.domain.rebate.model.entity.BehaviorRebateOrderEntity;
import cn.hzq.domain.rebate.model.entity.TaskEntity;
import cn.hzq.domain.rebate.model.valobj.DailyBehaviorRebateVO;
import cn.hzq.domain.rebate.model.valobj.TaskStateVO;
import cn.hzq.domain.rebate.repository.IBehaviorRebateRepository;
import cn.hzq.types.common.Constants;
import cn.hzq.types.event.BaseEvent;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/10
 * @Description 行为返利接口服务实现类
 **/
@Service
public class BehaviorRebateService implements IBehaviorRebateService {
    @Resource
    private IBehaviorRebateRepository behaviorRebateRepository;
    @Resource
    private SendRebateMessageEvent sendRebateMessageEvent;

    @Override
    public List<String> createOrder(BehaviorEntity behaviorEntity) {
        // 1.查询返利配置
        List<DailyBehaviorRebateVO> dailyBehaviorRebateVOS = behaviorRebateRepository.queryDailyBehaviorRebateConfig(behaviorEntity.getBehaviorTypeVO());
        if (null == dailyBehaviorRebateVOS || dailyBehaviorRebateVOS.isEmpty()) return new ArrayList<>();

        // 2.构建聚合对象
        ArrayList<String> orderIds = new ArrayList<>();
        ArrayList<BehaviorRebateAggregate> behaviorRebateAggregates = new ArrayList<>();
        for (DailyBehaviorRebateVO dailyBehaviorRebateVO : dailyBehaviorRebateVOS) {
            // 拼装业务Id:用户ID_返利类型_外部透传业务ID
            String bizId = behaviorEntity.getUserId() + Constants.UNDERLINE +
                    dailyBehaviorRebateVO.getRebateType() + Constants.UNDERLINE +
                    behaviorEntity.getOutBusinessNo();
            BehaviorRebateOrderEntity behaviorRebateOrderEntity =
                    BehaviorRebateOrderEntity.builder()
                            .userId(behaviorEntity.getUserId())
                            .orderId(RandomStringUtils.randomNumeric(12))
                            .behaviorType(dailyBehaviorRebateVO.getBehaviorType())
                            .rebateDesc(dailyBehaviorRebateVO.getRebateDesc())
                            .rebateConfig(dailyBehaviorRebateVO.getRebateConfig())
                            .rebateType(dailyBehaviorRebateVO.getRebateType())
                            .outBusinessNo(behaviorEntity.getOutBusinessNo())
                            .bizId(bizId)
                            .build();
            orderIds.add(behaviorRebateOrderEntity.getOrderId());
            // 构建MQ消息对象
            SendRebateMessageEvent.RebateMessage rebateMessage =
                    SendRebateMessageEvent.RebateMessage.builder()
                            .userId(behaviorEntity.getUserId())
                            .rebateType(dailyBehaviorRebateVO.getRebateType())
                            .rebateConfig(dailyBehaviorRebateVO.getRebateConfig())
                            .bizId(bizId)
                            .rebateDesc(dailyBehaviorRebateVO.getRebateDesc())
                            .build();
            // 构建事件消息
            BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage> rebateMessageEventMessage =
                    sendRebateMessageEvent.buildEventMessage(rebateMessage);
            // 组装任务对象
            TaskEntity taskEntity =
                    TaskEntity.builder()
                            .userId(behaviorEntity.getUserId())
                            .topic(sendRebateMessageEvent.topic())
                            .messageId(rebateMessageEventMessage.getId())
                            .message(rebateMessageEventMessage)
                            .state(TaskStateVO.create)
                            .build();
            // 构建聚合对象
            BehaviorRebateAggregate behaviorRebateAggregate = BehaviorRebateAggregate.builder()
                    .userId(behaviorEntity.getUserId())
                    .behaviorRebateOrderEntity(behaviorRebateOrderEntity)
                    .taskEntity(taskEntity)
                    .build();
            behaviorRebateAggregates.add(behaviorRebateAggregate);
        }

        // 3.存储聚合对象数据
        behaviorRebateRepository.saveUserRebateRecord(behaviorEntity.getUserId(), behaviorRebateAggregates);
        // 4.返回订单Id
        return orderIds;
    }

    @Override
    public List<BehaviorRebateOrderEntity> queryOrderByOutBusinessNo(String userId, String outBusinessNo) {
        return behaviorRebateRepository.queryOrderByOutBusinessNo(userId,outBusinessNo);
    }
}
