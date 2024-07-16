package cn.hzq.domain.activity.service.product;

import cn.hzq.domain.activity.model.entity.SkuProductEntity;
import cn.hzq.domain.activity.repository.IActivityRepository;
import cn.hzq.domain.activity.service.IRaffleActivitySkuProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-16
 * @Description: sku商品服务
 */
@Service
public class RaffleActivitySkuProductService implements IRaffleActivitySkuProductService {
    @Resource
    private IActivityRepository activityRepository;
    @Override
    public List<SkuProductEntity> querySkuProductEntityListByActivityId(Long activityId) {
        return activityRepository.querySkuProductEntityListByActivityId(activityId);
    }
}
