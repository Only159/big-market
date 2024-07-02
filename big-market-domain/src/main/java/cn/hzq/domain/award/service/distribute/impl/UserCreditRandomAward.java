package cn.hzq.domain.award.service.distribute.impl;

import cn.hzq.domain.award.model.aggregate.GiveOutPrizesAggregate;
import cn.hzq.domain.award.model.entity.DistributeAwardEntity;
import cn.hzq.domain.award.model.entity.UserAwardRecordEntity;
import cn.hzq.domain.award.model.entity.UserCreditAwardEntity;
import cn.hzq.domain.award.model.valobj.AwardStateVO;
import cn.hzq.domain.award.repository.IAwardRepository;
import cn.hzq.domain.award.service.distribute.IDistributeAward;
import cn.hzq.types.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author 黄照权
 * @description 用户积分奖品，支持 award_config 透传，满足黑名单积分奖励。
 */
@Component("user_credit_random")
public class UserCreditRandomAward implements IDistributeAward {

    @Resource
    private IAwardRepository repository;

    @Override
    public void giveOutPrizes(DistributeAwardEntity distributeAwardEntity) {
        // 奖品ID
        Integer awardId = distributeAwardEntity.getAwardId();
        // 奖品配置信息
        String awardConfig = distributeAwardEntity.getAwardConfig();
        if (StringUtils.isBlank(awardConfig)) {
            awardConfig = repository.queryAwardConfig(awardId);
        }
        String[] creditRange = awardConfig.split(Constants.SPLIT);
        if (creditRange.length != 2) {
            throw new RuntimeException("award_config 「" + awardConfig + "」配置不是一个范围值，如 1,100");
        }
        // 生成随机积分值
        BigDecimal randomPoints = generateRandom(new BigDecimal(creditRange[0]), new BigDecimal(creditRange[1]));
        // 构建聚合对象
        UserAwardRecordEntity userAwardRecordEntity = GiveOutPrizesAggregate.buildDistributeUserAwardRecordEntity(
                distributeAwardEntity.getUserId(),
                distributeAwardEntity.getOrderId(),
                distributeAwardEntity.getAwardId(),
                AwardStateVO.complete);
        UserCreditAwardEntity userCreditAwardEntity = GiveOutPrizesAggregate.buildUserCreditAwardEntity(distributeAwardEntity.getUserId(), randomPoints);

        GiveOutPrizesAggregate giveOutPrizesAggregate = new GiveOutPrizesAggregate();
        giveOutPrizesAggregate.setUserId(distributeAwardEntity.getUserId());
        giveOutPrizesAggregate.setUserCreditAwardEntity(userCreditAwardEntity);
        giveOutPrizesAggregate.setUserAwardRecordEntity(userAwardRecordEntity);

        //存储发奖对象
        repository.saveGiveOutPrizesAggregate(giveOutPrizesAggregate);
    }

    /**
     * 生成随机积分值
     *
     * @param min 最小值
     * @param max 最大值
     * @return 随机值
     */
    private BigDecimal generateRandom(BigDecimal min, BigDecimal max) {
        if (min.equals(max)) return min;
        BigDecimal randomBigDecimal = min.add(BigDecimal.valueOf(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.round(new MathContext(3));
    }

}
