package cn.hzq.trigger.job;

import cn.hzq.domain.activity.model.valobj.ActivitySkuStockVO;
import cn.hzq.domain.activity.service.IRaffleActivitySkuStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/5/4
 * @Description 趋势更新活动库存
 **/
@Slf4j
@Component
public class UpdateActivitySkuStockJob {
    @Resource
    private IRaffleActivitySkuStockService skuStock;

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec() {

        try {
            log.info("定时任务，更新活动sku库存【延迟队列获取，降低对数据库的更新频次，不要产生竞争】");
            ActivitySkuStockVO activitySkuStockVO = skuStock.takeQueueValue();
            if (null == activitySkuStockVO) return;
            log.info("定时任务，更新活动sku库存 sku:{} activityId:{}", activitySkuStockVO.getSku(), activitySkuStockVO.getActivityId());
            skuStock.updateActivitySkuStock(activitySkuStockVO.getSku());
        } catch (Exception e) {
            log.error("定时任务，更新奖品消耗库存失败", e);
        }
    }
}
