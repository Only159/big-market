package cn.hzq.trigger.api;

import cn.hzq.trigger.api.dto.*;
import cn.hzq.types.model.Response;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/6
 * @Description 抽奖活动服务
 **/
public interface IRaffleActivityService {
    /**
     * 活动装配，数据预热到缓存
     *
     * @param activityId 活动Id
     * @return 装配结果
     */
    Response<Boolean> armory(Long activityId);

    /**
     * 活动抽奖接口
     *
     * @param requestDTO 请求对象
     * @return 返回结果
     */
    Response<ActivityDrawResponseDTO> draw(ActivityDrawRequestDTO requestDTO);

    /**
     * 日历签到返利接口
     *
     * @param userId 用户Id
     * @return 签到结果
     */
    Response<Boolean> calendarSingRebate(String userId);

    /**
     * 判断是否完成日历签到返利接口
     *
     * @param userId 用户Id
     * @return 完成结果
     */
    Response<Boolean> isCalendarSingRebate(String userId);

    /**
     * 查询活动账户抽奖次数
     *
     * @param requestDTO 请求参数对象
     * @return 结果对象
     */
    Response<UserActivityAccountResponseDTO> queryUserActivityAccount(UserActivityAccountRequestDTO requestDTO);

    /**
     * 查询sku商品集合
     *
     * @param activityId 活动ID
     * @return 商品集合
     */
    Response<List<SkuProductResponseDTO>> querySkuProductListByActivityId(Long activityId);

    /**
     * 查询用户积分值
     *
     * @param userId 用户ID
     * @return 可用积分
     */
    Response<BigDecimal> queryUserCreditAccount(String userId);

    /**
     * 积分支付兑换商品
     *
     * @param request 请求对象「用户ID、商品ID」
     * @return 兑换结果
     */
    Response<Boolean> creditPayExchangeSku(SkuProductShopCartRequestDTO request);

}
