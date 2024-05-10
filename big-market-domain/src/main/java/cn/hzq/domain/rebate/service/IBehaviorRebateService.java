package cn.hzq.domain.rebate.service;

import cn.hzq.domain.rebate.model.entity.BehaviorEntity;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/5/10
 * @Description 行为返利接口服务
 **/
public interface IBehaviorRebateService {
    List<String> createOrder(BehaviorEntity behaviorEntity);
}
