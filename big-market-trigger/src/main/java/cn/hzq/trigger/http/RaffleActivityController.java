package cn.hzq.trigger.http;

import cn.hzq.domain.activity.model.entity.*;
import cn.hzq.domain.activity.model.valobj.OrderTradeTypeVO;
import cn.hzq.domain.activity.service.IRaffleActivityAccountQuotaService;
import cn.hzq.domain.activity.service.IRaffleActivityPartakeService;
import cn.hzq.domain.activity.service.IRaffleActivitySkuProductService;
import cn.hzq.domain.activity.service.armory.IActivityArmory;
import cn.hzq.domain.award.model.entity.UserAwardRecordEntity;
import cn.hzq.domain.award.model.valobj.AwardStateVO;
import cn.hzq.domain.award.service.IAwardService;
import cn.hzq.domain.credit.model.entity.CreditAccountEntity;
import cn.hzq.domain.credit.model.entity.TradeEntity;
import cn.hzq.domain.credit.model.valobj.TradeNameVO;
import cn.hzq.domain.credit.model.valobj.TradeTypeVO;
import cn.hzq.domain.credit.service.ICreditAdjustService;
import cn.hzq.domain.rebate.model.entity.BehaviorEntity;
import cn.hzq.domain.rebate.model.entity.BehaviorRebateOrderEntity;
import cn.hzq.domain.rebate.model.valobj.BehaviorTypeVO;
import cn.hzq.domain.rebate.service.IBehaviorRebateService;
import cn.hzq.domain.strategy.model.entity.RaffleAwardEntity;
import cn.hzq.domain.strategy.model.entity.RaffleFactorEntity;
import cn.hzq.domain.strategy.service.IRaffleStrategy;
import cn.hzq.domain.strategy.service.armory.IStrategyArmory;
import cn.hzq.trigger.api.IRaffleActivityService;
import cn.hzq.trigger.api.dto.*;
import cn.hzq.types.enums.ResponseCode;
import cn.hzq.types.exception.AppException;
import cn.hzq.types.model.Response;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/6
 * @Description 抽奖活动服务
 **/
@Slf4j
@RestController
@CrossOrigin("${app.config.cross-origin}")
@RequestMapping("/api/${app.config.api-version}/raffle/activity")
public class RaffleActivityController implements IRaffleActivityService {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @Resource
    private IActivityArmory activityArmory;
    @Resource
    private IStrategyArmory strategyArmory;
    @Resource
    private IRaffleActivityAccountQuotaService raffleActivityAccountQuotaService;
    @Resource
    private IRaffleActivityPartakeService raffleActivityPartakeService;
    @Resource
    private IRaffleStrategy raffleStrategy;
    @Resource
    private IAwardService awardService;
    @Resource
    private IBehaviorRebateService behaviorRebateService;
    @Resource
    private IRaffleActivitySkuProductService raffleActivitySkuProductService;
    @Resource
    private ICreditAdjustService creditAdjustService;

    @RequestMapping(value = "credit_pay_exchange_sku", method = RequestMethod.POST)
    @Override
    public Response<Boolean> creditPayExchangeSku(SkuProductShopCartRequestDTO request) {
        try {
            log.info("积分兑换商品开始 userId:{} sku:{}", request.getUserId(), request.getSku());
            // 1. 创建兑换商品sku订单，outBusinessNo 每次创建出一个单号。
            UnpaidActivityOrderEntity unpaidActivityOrder = raffleActivityAccountQuotaService.createOrder(SkuRechargeEntity.builder()
                    .userId(request.getUserId())
                    .sku(request.getSku())
                    .outBusinessNo(RandomStringUtils.randomNumeric(12))
                    .orderTradeType(OrderTradeTypeVO.credit_pay_trade)
                    .build());
            log.info("积分兑换商品，创建订单完成 userId:{} sku:{} outBusinessNo:{}", request.getUserId(), request.getSku(), unpaidActivityOrder.getOutBusinessNo());

            // 2.支付兑换商品
            String orderId = creditAdjustService.createOrder(TradeEntity.builder()
                    .userId(unpaidActivityOrder.getUserId())
                    .tradeName(TradeNameVO.CONVERT_SKU)
                    .tradeType(TradeTypeVO.REVERSE)
                    .amount(unpaidActivityOrder.getPayAmount())
                    .outBusinessNo(unpaidActivityOrder.getOutBusinessNo())
                    .build());
            log.info("积分兑换商品，支付订单完成  userId:{} sku:{} orderId:{}", request.getUserId(), request.getSku(), orderId);

            return Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(true)
                    .build();
        } catch (Exception e) {
            log.error("积分兑换商品失败 userId:{} sku:{}", request.getUserId(), request.getSku(), e);
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .data(false)
                    .build();

        }
    }

    @Override
    public Response<BigDecimal> queryUserCreditAccount(String userId) {
        try {
            log.info("查询用户积分账户开始，userId:{}", userId);
            CreditAccountEntity creditAccountEntity = creditAdjustService.queryUserCreditAccount(userId);
            log.info("查询用户积分值完成 userId:{} adjustAmount:{}", userId, creditAccountEntity.getAdjustAmount());
            return Response.<BigDecimal>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(creditAccountEntity.getAdjustAmount())
                    .build();
        } catch (Exception e) {
            log.error("查询用户积分值失败 userId:{}", userId, e);
            return Response.<BigDecimal>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();

        }
    }


    @Override
    public Response<List<SkuProductResponseDTO>> querySkuProductListByActivityId(Long activityId) {
        try {
            log.info("查询sku商品集合开始，activity:{}", activityId);
            // 参数校验
            if (null == activityId) {
                throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
            }
            // 查询商品&封装数据
            List<SkuProductEntity> skuProductEntities = raffleActivitySkuProductService.querySkuProductEntityListByActivityId(activityId);
            List<SkuProductResponseDTO> skuProductResponseDTOS = new ArrayList<>(skuProductEntities.size());
            skuProductEntities.forEach(skuProductEntity -> {

                SkuProductResponseDTO.ActivityCount activityCount = new SkuProductResponseDTO.ActivityCount();
                activityCount.setTotalCount(skuProductEntity.getActivityCount().getTotalCount());
                activityCount.setMonthCount(skuProductEntity.getActivityCount().getMonthCount());
                activityCount.setDayCount(skuProductEntity.getActivityCount().getDayCount());

                SkuProductResponseDTO skuProductResponseDTO = new SkuProductResponseDTO();
                skuProductResponseDTO.setSku(skuProductEntity.getSku());
                skuProductResponseDTO.setActivityId(skuProductEntity.getActivityId());
                skuProductResponseDTO.setActivityCountId(skuProductEntity.getActivityCountId());
                skuProductResponseDTO.setStockCount(skuProductEntity.getStockCount());
                skuProductResponseDTO.setStockCountSurplus(skuProductEntity.getStockCountSurplus());
                skuProductResponseDTO.setProductAmount(skuProductEntity.getProductAmount());
                skuProductResponseDTO.setActivityCount(activityCount);
                skuProductResponseDTOS.add(skuProductResponseDTO);

            });
            log.info("查询sku商品集合完成 activityId:{} skuProductResponseDTOS:{}", activityId, JSON.toJSONString(skuProductResponseDTOS));
            return Response.<List<SkuProductResponseDTO>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(skuProductResponseDTOS)
                    .build();
        } catch (Exception e) {
            log.error("查询sku商品集合失败，activity:{}", activityId, e);
            return Response.<List<SkuProductResponseDTO>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }


    @Override
    @GetMapping("armory")
    public Response<Boolean> armory(@RequestParam Long activityId) {
        try {
            log.info("活动装配，数据预热【开始】 activityId:{}", activityId);
            // 活动装配
            activityArmory.assembleActivitySkuByActivityId(activityId);
            // 策略装配
            strategyArmory.assembleLotteryStrategyByActivityId(activityId);

            Response<Boolean> response = Response.<Boolean>builder().code(ResponseCode.SUCCESS.getCode()).info(ResponseCode.SUCCESS.getInfo()).data(true).build();

            log.info("活动装配，数据预热【完成】 activityId:{}", activityId);
            return response;
        } catch (Exception e) {
            log.error("活动装配，数据预热【失败】 activityId:{}", activityId, e);
            return Response.<Boolean>builder().code(ResponseCode.UN_ERROR.getCode()).info(ResponseCode.UN_ERROR.getInfo()).data(false).build();
        }
    }


    @Override
    @PostMapping("draw")
    public Response<ActivityDrawResponseDTO> draw(@RequestBody ActivityDrawRequestDTO requestDTO) {
        try {
            log.info("活动抽奖 userId:{} activityId{}", requestDTO.getUserId(), requestDTO.getActivityId());
            // 1、参数校验
            if (StringUtils.isBlank(requestDTO.getUserId()) || null == requestDTO.getActivityId()) {
                throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
            }
            // 2、参与活动-创建参加记录订单
            UserRaffleOrderEntity orderEntity = raffleActivityPartakeService.createOrder(requestDTO.getUserId(), requestDTO.getActivityId());
            log.info("活动抽奖，创建订单。userId:{} activityId:{} orderId:{}", orderEntity.getUserId(), orderEntity.getActivityId(), orderEntity.getOrderId());

            // 3、抽奖策略-执行抽奖
            RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(RaffleFactorEntity.builder().userId(orderEntity.getUserId()).strategyId(orderEntity.getStrategyId()).endDateTime(orderEntity.getEndDateTime()).build());
            // 4、存放结果-写入中奖记录
            UserAwardRecordEntity userAwardRecord = UserAwardRecordEntity.builder()
                    .userId(orderEntity.getUserId())
                    .activityId(orderEntity.getActivityId())
                    .strategyId(orderEntity.getStrategyId())
                    .orderId(orderEntity.getOrderId())
                    .awardId(raffleAwardEntity.getAwardId())
                    .awardTitle(raffleAwardEntity.getAwardTitle())
                    .awardTime(new Date()).awardState(AwardStateVO.create)
                    .awardConfig(raffleAwardEntity.getAwardConfig())
                    .build();
            awardService.saveUserAwardRecord(userAwardRecord);
            // 5、中奖结果返回
            return Response.<ActivityDrawResponseDTO>builder().code(ResponseCode.SUCCESS.getCode()).info(ResponseCode.SUCCESS.getInfo()).data(ActivityDrawResponseDTO.builder().awardId(raffleAwardEntity.getAwardId()).awardTitle(raffleAwardEntity.getAwardTitle()).awardIndex(raffleAwardEntity.getSort()).build()).build();

        } catch (AppException e) {
            log.error("抽奖活动失败。 userId:{} activityId:{}", requestDTO.getUserId(), requestDTO.getActivityId(), e);
            return Response.<ActivityDrawResponseDTO>builder().code(e.getCode()).info(e.getInfo()).build();

        } catch (Exception e) {
            log.error("抽奖活动失败。 userId:{} activityId:{}", requestDTO.getUserId(), requestDTO.getActivityId(), e);
            return Response.<ActivityDrawResponseDTO>builder().code(ResponseCode.UN_ERROR.getCode()).info(ResponseCode.UN_ERROR.getInfo()).build();
        }
    }

    @PostMapping("calendar_sing_rebate")
    @Override
    public Response<Boolean> calendarSingRebate(@RequestParam String userId) {
        try {
            log.info("日历签到返利开始。userId:{}", userId);
            BehaviorEntity behaviorEntity = BehaviorEntity.builder().userId(userId).behaviorTypeVO(BehaviorTypeVO.SIGN).outBusinessNo(dateFormat.format(new Date())).build();
            List<String> orderIds = behaviorRebateService.createOrder(behaviorEntity);
            log.info("日历签到返利完成。userId:{}，orderIds:{}", userId, JSON.toJSONString(orderIds));
            return Response.<Boolean>builder().code(ResponseCode.SUCCESS.getCode()).info(ResponseCode.SUCCESS.getInfo()).data(true).build();
        } catch (AppException e) {
            log.info("日历签到返利异常。userId:{}", userId, e);
            return Response.<Boolean>builder().code(e.getCode()).info(e.getInfo()).build();
        } catch (Exception e) {
            log.info("日历签到返利失败。userId:{}", userId, e);
            return Response.<Boolean>builder().code(ResponseCode.UN_ERROR.getCode()).info(ResponseCode.UN_ERROR.getInfo()).data(false).build();
        }
    }

    @PostMapping("is_calendar_sing_rebate")
    @Override
    public Response<Boolean> isCalendarSingRebate(@RequestParam String userId) {
        try {
            log.info("查询用户是否完成日历签到返利开始，userId:{}", userId);
            String outBusinessNo = dateFormat.format(new Date());
            List<BehaviorRebateOrderEntity> behaviorRebateOrderEntities = behaviorRebateService.queryOrderByOutBusinessNo(userId, outBusinessNo);
            log.info("查询用户是否完成日历签到返利完成，userId:{},结果：{}", userId, behaviorRebateOrderEntities.size());
            return Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(!behaviorRebateOrderEntities.isEmpty())
                    .build();
        } catch (Exception e) {
            log.info("查询用户是否完成日历签到返利失败，userId:{}", userId, e);
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .data(false)
                    .build();
        }
    }

    /**
     * 通过用户ID和活动ID查询活动账户次数
     *
     * @param requestDTO 请求参数对象
     * @return 账户次数
     */
    @PostMapping("query_user_activity_account")
    @Override
    public Response<UserActivityAccountResponseDTO> queryUserActivityAccount(@RequestBody UserActivityAccountRequestDTO requestDTO) {
        try {
            log.info("查询用户活动账户开始。userId:{},activityId:{}", requestDTO.getActivityId(), requestDTO.getActivityId());
            // 1、参数校验
            if (StringUtils.isBlank(requestDTO.getUserId()) || null == requestDTO.getActivityId()) {
                throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
            }
            ActivityAccountEntity activityAccountEntity = raffleActivityAccountQuotaService.queryActivityAccountEntity(requestDTO.getUserId(), requestDTO.getActivityId());
            UserActivityAccountResponseDTO userActivityAccountResponseDTO = UserActivityAccountResponseDTO.builder()
                    .totalCount(activityAccountEntity.getTotalCount())
                    .totalCountSurplus(activityAccountEntity.getTotalCountSurplus())
                    .dayCount(activityAccountEntity.getDayCount())
                    .dayCountSurplus(activityAccountEntity.getDayCountSurplus())
                    .monthCount(activityAccountEntity.getMonthCount())
                    .monthCountSurplus(activityAccountEntity.getMonthCountSurplus())
                    .build();
            log.info("查询用户活动账户完成。userId:{},activityId:{}，dto：{}", requestDTO.getActivityId(), requestDTO.getActivityId(), JSON.toJSONString(userActivityAccountResponseDTO));
            return Response.<UserActivityAccountResponseDTO>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(userActivityAccountResponseDTO)
                    .build();
        } catch (AppException e) {
            log.error("查询用户活动账户失败。userId:{},activityId:{}", requestDTO.getActivityId(), requestDTO.getActivityId(), e);
            return Response.<UserActivityAccountResponseDTO>builder().code(e.getCode()).info(e.getInfo()).build();
        } catch (Exception e) {
            log.error("查询用户活动账户失败。userId:{},activityId:{}", requestDTO.getActivityId(), requestDTO.getActivityId(), e);
            return Response.<UserActivityAccountResponseDTO>builder().code(ResponseCode.UN_ERROR.getCode()).info(ResponseCode.UN_ERROR.getInfo()).build();
        }
    }

}

