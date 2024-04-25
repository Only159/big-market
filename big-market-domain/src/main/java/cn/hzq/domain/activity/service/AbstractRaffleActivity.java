package cn.hzq.domain.activity.service;

import cn.hzq.domain.activity.model.aggregate.CreateOrderAggregate;
import cn.hzq.domain.activity.model.entity.ActivityCountEntity;
import cn.hzq.domain.activity.model.entity.ActivityEntity;
import cn.hzq.domain.activity.model.entity.ActivitySkuEntity;
import cn.hzq.domain.activity.model.entity.SkuRechargeEntity;
import cn.hzq.domain.activity.repository.IActivityRepository;
import cn.hzq.domain.activity.service.rule.IActionChain;
import cn.hzq.domain.activity.service.rule.factory.DefaultActivityChainFactory;
import cn.hzq.types.enums.ResponseCode;
import cn.hzq.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 黄照权
 * @Date 2024/4/24
 * @Description 抽奖活动抽象类，定义标准的流程
 **/
@Slf4j
public abstract class AbstractRaffleActivity extends RaffleActivitySupport implements IRaffleOrder {

    public AbstractRaffleActivity(DefaultActivityChainFactory defaultActivityChainFactory, IActivityRepository activityRepository) {
        super(defaultActivityChainFactory, activityRepository);
    }

    @Override
    public String createSkuRechargeOrder(SkuRechargeEntity skuRechargeEntity) {
        // 1、参数校验
        String userId = skuRechargeEntity.getUserId();
        Long sku = skuRechargeEntity.getSku();
        String outBusinessNo = skuRechargeEntity.getOutBusinessNo();
        if (sku == null || StringUtils.isBlank(userId) || StringUtils.isBlank(outBusinessNo)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        // 2、查询基础信息
        // 2.1 通过sku查询活动信息
        ActivitySkuEntity activitySkuEntity = queryActivitySku(sku);
        // 2.2 查询活动详细信息
        ActivityEntity activityEntity = queryRaffleActivityByActivityId(activitySkuEntity.getActivityId());
        // 2.3 查询次数信息（用户在活动上可参与的次数）
        ActivityCountEntity activityCountEntity = queryRaffleActivityCountByActivityCountId(activitySkuEntity.getActivityCountId());

        // 3、活动规则校验
        IActionChain actionChain = defaultActivityChainFactory.openActionChain();
        boolean success = actionChain.action(activitySkuEntity, activityEntity, activityCountEntity);

        // 4、构建订单聚合对象
        CreateOrderAggregate createOrderAggregate = buildOrderAggregate(skuRechargeEntity, activitySkuEntity, activityEntity, activityCountEntity);

        // 5、保存订单
        doSaveOrder(createOrderAggregate);

        // 6、返回单号
        return createOrderAggregate.getActivityOrderEntity().getOrderId();
    }

    /**
     * 保存订单
     *
     * @param createOrderAggregate 下单聚合对象
     */

    protected abstract void doSaveOrder(CreateOrderAggregate createOrderAggregate);

    /**
     * 创建订单聚合对象
     *
     * @param skuRechargeEntity   活动商品充值实体对象
     * @param activitySkuEntity   活动sku实体对象
     * @param activityEntity      活动实体对象
     * @param activityCountEntity 活动次数实体对象
     * @return 下单聚合对象
     */
    protected abstract CreateOrderAggregate buildOrderAggregate(SkuRechargeEntity skuRechargeEntity, ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);
}
