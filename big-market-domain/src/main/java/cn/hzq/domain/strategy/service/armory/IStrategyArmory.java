package cn.hzq.domain.strategy.service.armory;

/**
 * @author 黄照权
 * @Date 2024/3/11
 * @Description 策略装配库（兵工厂），负责初始化策略计算
 **/
public interface IStrategyArmory {

    void  assembleLotteryStrategy(Long strategyId);

    Integer getRandomAwardId(Long strategyId);
}
