package cn.hzq.domain.activity.service.armory;

/**
 * @author 黄照权
 * @Date 2024/5/4
 * @Description 活动装配预热
 **/
public interface IActivityArmory {
    /**
     * 通过活动Id装配活动
     * @param activityId 活动Id
     * @return 装配结果
     */
    boolean assembleActivitySkuByActivityId(Long activityId);
    /**
     * 通过活动SKU装配活动
     * @param sku 活动sku
     * @return 装配结果
     */
    boolean assembleActivitySku(Long sku);
}
