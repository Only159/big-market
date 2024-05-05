package cn.hzq.domain.activity.service.quota.rule;

/**
 * @author 黄照权
 * @Date 2024/4/25
 * @Description 责任链装配接口
 **/
public interface IActionChainArmory {
    IActionChain next();
    IActionChain appendNext(IActionChain next);
}
