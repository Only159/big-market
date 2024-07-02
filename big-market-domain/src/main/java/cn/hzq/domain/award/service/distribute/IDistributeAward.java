package cn.hzq.domain.award.service.distribute;

import cn.hzq.domain.award.model.entity.DistributeAwardEntity;

/**
 * @author 黄照权
 * @description 分发奖品接口
 * @create 2024-05-18 08:22
 */

public interface   IDistributeAward {
    void giveOutPrizes(DistributeAwardEntity distributeAwardEntity);

}
