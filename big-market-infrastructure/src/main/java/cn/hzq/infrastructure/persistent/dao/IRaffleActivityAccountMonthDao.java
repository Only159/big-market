package cn.hzq.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.hzq.infrastructure.persistent.po.RaffleActivityAccountMonth;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 抽奖活动账户表-月次数
 **/
@Mapper
public interface IRaffleActivityAccountMonthDao {
    @DBRouter
    RaffleActivityAccountMonth queryActivityAccountMonthByUserId(RaffleActivityAccountMonth raffleActivityAccountMonthReq);

    int updateActivityAccountMonthSubtractionQuota(RaffleActivityAccountMonth raffleActivityAccountMonth);

    void insertActivityAccountMonth(RaffleActivityAccountMonth raffleActivityAccountMonth);

    /**
     * 账户添加次数，存在更新，不存在不管
     *
     * @param raffleActivityAccountMonth 抽奖活动月次数
     */
    void addAccountQuota(RaffleActivityAccountMonth raffleActivityAccountMonth);
}
