package cn.hzq.domain.award.service;

import cn.hzq.domain.award.model.entity.UserAwardRecordEntity;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 奖品服务接口
 **/
public interface IAwardService {
    /**
     * 保存用户奖品订单
     * @param userAwardRecordEntity 用户中奖记录实体对象
     */
    void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity);
}
