package cn.hzq.domain.strategy.service.rule.chain;

/**
 * @author 黄照权
 * @Date 2024/3/13
 * @Description 责任链装配接口
 **/
public interface ILogicChainArmory {
    ILogicChain appendNext(ILogicChain next);

    ILogicChain next();
}
