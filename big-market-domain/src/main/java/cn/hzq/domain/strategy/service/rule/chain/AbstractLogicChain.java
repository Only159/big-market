package cn.hzq.domain.strategy.service.rule.chain;

/**
 * @author 黄照权
 * @Date 2024/3/13
 * @Description
 **/
public abstract class AbstractLogicChain implements ILogicChain{
    private ILogicChain next;

    @Override
    public ILogicChain appendNext(ILogicChain next) {
        this.next = next;
        return next;
    }

    @Override
    public ILogicChain next() {
        return next;
    }
    protected  abstract String ruleModel();
}
