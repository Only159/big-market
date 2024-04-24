package cn.hzq.infrastructure.persistent.repository;

import cn.hzq.domain.activity.model.entity.ActivityCountEntity;
import cn.hzq.domain.activity.model.entity.ActivityEntity;
import cn.hzq.domain.activity.model.entity.ActivitySkuEntity;
import cn.hzq.domain.activity.model.valobj.ActivityStateVO;
import cn.hzq.domain.activity.repository.IActivityRepository;
import cn.hzq.infrastructure.persistent.dao.IRaffleActivityCountDao;
import cn.hzq.infrastructure.persistent.dao.IRaffleActivityDao;
import cn.hzq.infrastructure.persistent.dao.IRaffleActivitySkuDao;
import cn.hzq.infrastructure.persistent.po.RaffleActivity;
import cn.hzq.infrastructure.persistent.po.RaffleActivityCount;
import cn.hzq.infrastructure.persistent.po.RaffleActivitySku;
import cn.hzq.infrastructure.persistent.redis.IRedisService;
import cn.hzq.types.common.Constants;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/4/24
 * @Description 活动仓储服务
 **/
@Repository
public class ActivityRepository implements IActivityRepository {
    @Resource
    private IRedisService redisService;
    @Resource
    private IRaffleActivitySkuDao raffleActivitySkuDao;
    @Resource
    private IRaffleActivityDao activityDao;
    @Resource
    private IRaffleActivityCountDao raffleActivityCountDao;

    @Override
    public ActivitySkuEntity queryActivitySkuBySku(Long sku) {
        // 优先从缓存获取
        String cacheKey = Constants.RedisKey.ACTIVITY_SKU_KEY + sku;
        ActivitySkuEntity activitySkuEntity = redisService.getValue(cacheKey);
        if (null != activitySkuEntity) return activitySkuEntity;

        // 从数据库获取
        RaffleActivitySku raffleActivitySku = raffleActivitySkuDao.queryRaffleActivitySkuBySku(sku);
        activitySkuEntity = ActivitySkuEntity.builder()
                .sku(raffleActivitySku.getSku())
                .activityId(raffleActivitySku.getActivityId())
                .activityCountId(raffleActivitySku.getActivityCountId())
                .stockCount(raffleActivitySku.getStockCount())
                .stockCountSurplus(raffleActivitySku.getStockCountSurplus())
                .build();
        // 存入缓存
        redisService.setValue(cacheKey, activitySkuEntity);
        //返回结果
        return activitySkuEntity;

    }

    @Override
    public ActivityEntity queryRaffleActivityByActivityId(Long activityId) {
        // 优先从缓存获取
        String cacheKey = Constants.RedisKey.ACTIVITY_KEY + activityId;
        ActivityEntity activityEntity = redisService.getValue(cacheKey);
        if (null != activityEntity) return activityEntity;

        // 从数据库获取
        RaffleActivity raffleActivity = activityDao.queryRaffleActivityByActivityId(activityId);
        activityEntity = ActivityEntity.builder()
                .activityId(raffleActivity.getActivityId())
                .activityName(raffleActivity.getActivityName())
                .activityDesc(raffleActivity.getActivityDesc())
                .beginDateTime(raffleActivity.getBeginDateTime())
                .endDateTime(raffleActivity.getEndDateTime())
                .strategyId(raffleActivity.getStrategyId())
                .state(ActivityStateVO.valueOf(raffleActivity.getState()))
                .build();
        // 存入缓存
        redisService.setValue(cacheKey, activityEntity);
        // 返回结果
        return activityEntity;
    }

    @Override
    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId) {
        // 优先从缓存获取
        String cacheKey = Constants.RedisKey.ACTIVITY_COUNT_KEY + activityCountId;
        ActivityCountEntity activityCountEntity = redisService.getValue(cacheKey);
        if (null != activityCountEntity) return activityCountEntity;

        // 从库中获取数据
        RaffleActivityCount raffleActivityCount =
                raffleActivityCountDao.queryRaffleActivityCountByActivityCountId(activityCountId);
        activityCountEntity = ActivityCountEntity.builder()
                .activityCountId(raffleActivityCount.getActivityCountId())
                .totalCount(raffleActivityCount.getTotalCount())
                .dayCount(raffleActivityCount.getDayCount())
                .monthCount(raffleActivityCount.getMonthCount())
                .build();
        // 放入缓存
        redisService.setValue(cacheKey, activityCountEntity);

        // 返回结果
        return activityCountEntity;

    }
}
