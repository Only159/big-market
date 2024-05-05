package cn.hzq.domain.award.repository;

import cn.hzq.domain.award.model.aggregate.UserAwardRecordAggregate;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 奖品仓储层服务
 **/
public interface IAwardRepository {
    /**
     * 保存用户中奖记录
     * @param userAwardRecordAggregate 用户中奖记录聚合对象
     */
    void saveUserAwardRecord(UserAwardRecordAggregate userAwardRecordAggregate);
}
