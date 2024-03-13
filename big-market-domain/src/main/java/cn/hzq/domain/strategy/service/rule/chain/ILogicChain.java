package cn.hzq.domain.strategy.service.rule.chain;

/**
 * @author 黄照权
 * @Date 2024/3/13
 * @Description 责任链接口
 **/
public interface ILogicChain extends ILogicChainArmory{
    /**
     * @param userId     用户Id
     * @param strategyId 策略Id
     * @return 奖品ID
     * @Author HZQ
     * @Date 2024/3/13
     * @Description 责任链接口
     */
    Integer logic(String userId, Long strategyId);

}
