package cn.hzq.domain.activity.service.quota.rule.impl;

import cn.hzq.domain.activity.model.entity.ActivityCountEntity;
import cn.hzq.domain.activity.model.entity.ActivityEntity;
import cn.hzq.domain.activity.model.entity.ActivitySkuEntity;
import cn.hzq.domain.activity.model.valobj.ActivitySkuStockVO;
import cn.hzq.domain.activity.repository.IActivityRepository;
import cn.hzq.domain.activity.service.armory.IActivityDispatch;
import cn.hzq.domain.activity.service.quota.rule.AbstractActionChain;
import cn.hzq.types.enums.ResponseCode;
import cn.hzq.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/4/25
 * @Description 商品库存规则节点
 **/
@Slf4j
@Component("activity_sku_stock_action")
public class ActivitySkuStockActionChain extends AbstractActionChain {
    @Resource
    private IActivityDispatch activityDispatch;
    @Resource
    private IActivityRepository activityRepository;

    @Override
    public boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {
        log.info("活动责任链-基础信息【有效期、状态、库存(sku)】校验开始。sku：{} activityId:{}", activitySkuEntity.getSku(), activityEntity.getActivityId());
        // 扣减库存
        boolean status = activityDispatch.subtractionActivitySkuStock(activitySkuEntity.getSku(), activityEntity.getEndDateTime());
        // true 库存扣减成功
        if (status) {
            log.info("活动责任链-基础信息【有效期、状态、库存(sku)】成功。sku：{} activityId:{}", activitySkuEntity.getSku(), activityEntity.getActivityId());
            // 写入延迟队列 延迟消费更新库存
            activityRepository.activitySkuStockConsumeSendQueue(
                    ActivitySkuStockVO.builder()
                            .sku(activitySkuEntity.getSku())
                            .activityId(activityEntity.getActivityId())
                            .build());
            return true;
        }
        throw new AppException(ResponseCode.ACTIVITY_SKU_STOCK_ERROR.getCode(), ResponseCode.ACTIVITY_SKU_STOCK_ERROR.getInfo());

    }
}
