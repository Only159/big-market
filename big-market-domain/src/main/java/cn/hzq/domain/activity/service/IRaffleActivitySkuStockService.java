package cn.hzq.domain.activity.service;

import cn.hzq.domain.activity.model.valobj.ActivitySkuStockVO;

/**
 * @author 黄照权
 * @Date 2024/5/4
 * @Description 抽奖活动SKU库存服务接口
 **/
public interface IRaffleActivitySkuStockService {
    /**
     * 获取活动sku 库存消耗队列
     *
     * @return 奖品库存Key信息
     * @throws InterruptedException 异常
     */
    ActivitySkuStockVO takeQueueValue() throws InterruptedException;

    /**
     * 清空队列
     */
    void clearQueueValue();

    /**
     * 延迟队列 + 任务趋势更新活动库存
     * @param sku 活动sku
     */

    void updateActivitySkuStock(Long sku);

    /**
     * 缓存库存消耗完毕，清空数据库库存
     * @param sku 活动sku
     */
    void clearActivitySkuStock(Long sku);

}
