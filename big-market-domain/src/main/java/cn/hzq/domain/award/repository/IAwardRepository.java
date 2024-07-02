package cn.hzq.domain.award.repository;

import cn.hzq.domain.award.model.aggregate.GiveOutPrizesAggregate;
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

    /**
     * 保存发放奖品
     * @param giveOutPrizesAggregate 发放奖品聚合对象
     */
    void saveGiveOutPrizesAggregate(GiveOutPrizesAggregate giveOutPrizesAggregate);

    /**
     * 查询奖品配置信息
     * @param awardId 奖品Id
     * @return 奖品配置信息
     */
    String queryAwardConfig(Integer awardId);

    /**
     * 查询奖品Key
     * @param awardId 奖品id
     * @return 唯一Key
     */
    String queryAwardKey(Integer awardId);


}
