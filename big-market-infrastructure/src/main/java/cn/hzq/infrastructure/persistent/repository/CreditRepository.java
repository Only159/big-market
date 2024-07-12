package cn.hzq.infrastructure.persistent.repository;

import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import cn.hzq.domain.award.model.valobj.AccountStatusVO;
import cn.hzq.domain.credit.model.aggregate.TradeAggregate;
import cn.hzq.domain.credit.model.entity.CreditAccountEntity;
import cn.hzq.domain.credit.model.entity.CreditOrderEntity;
import cn.hzq.domain.credit.repository.ICreditRepository;
import cn.hzq.infrastructure.persistent.dao.IUserCreditAccountDao;
import cn.hzq.infrastructure.persistent.dao.IUserCreditOrderDao;
import cn.hzq.infrastructure.persistent.po.UserCreditAccount;
import cn.hzq.infrastructure.persistent.po.UserCreditOrder;
import cn.hzq.infrastructure.persistent.redis.IRedisService;
import cn.hzq.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-11
 * @Description: 积分域仓储服务实现
 */
@Slf4j
@Repository
public class CreditRepository implements ICreditRepository {
    @Resource
    private IUserCreditOrderDao userCreditOrderDao;
    @Resource
    private IUserCreditAccountDao userCreditAccountDao;
    @Resource
    private IRedisService redisService;
    @Resource
    private IDBRouterStrategy dbRouter;
    @Resource
    private TransactionTemplate transactionTemplate;


    @Override
    public void saveUserCreditTradeOrder(TradeAggregate tradeAggregate) {
        // 获取参数
        String userId = tradeAggregate.getUserId();
        CreditOrderEntity creditOrderEntity = tradeAggregate.getCreditOrderEntity();
        CreditAccountEntity creditAccountEntity = tradeAggregate.getCreditAccountEntity();
        // 创建积分账户对象
        UserCreditAccount userCreditAccountReq = new UserCreditAccount();
        userCreditAccountReq.setUserId(userId);
        userCreditAccountReq.setTotalAmount(creditAccountEntity.getAdjustAmount());
        userCreditAccountReq.setAvailableAmount(creditAccountEntity.getAdjustAmount());
        userCreditAccountReq.setAccountStatus(AccountStatusVO.open.getCode());
        // 创建积分订单对象
        UserCreditOrder userCreditOrderReq = new UserCreditOrder();
        userCreditOrderReq.setUserId(userId);
        userCreditOrderReq.setOrderId(creditOrderEntity.getOrderId());
        userCreditOrderReq.setTradeName(creditOrderEntity.getTradeName().getName());
        userCreditOrderReq.setTradeType(creditOrderEntity.getTradeType().getCode());
        userCreditOrderReq.setTradeAmount(creditOrderEntity.getTradeAmount());
        userCreditOrderReq.setOutBusinessNo(creditOrderEntity.getOutBusinessNo());
        // 获取锁
        RLock lock = redisService.getLock(
                Constants.RedisKey.USER_CREDIT_ACCOUNT_LOCK + userId + Constants.UNDERLINE + creditOrderEntity.getOutBusinessNo());
        try {
            lock.lock(3, TimeUnit.SECONDS);
            dbRouter.doRouter(userId);
            // 编程式事务
            transactionTemplate.execute(status -> {
                try {
                    // 1. 保存账户积分
                    UserCreditAccount userCreditAccount = userCreditAccountDao.queryUserCreditAccount(userCreditAccountReq);
                    if (null == userCreditAccount) {
                        userCreditAccountDao.insert(userCreditAccountReq);
                    } else {
                        userCreditAccountDao.updateAddAmount(userCreditAccountReq);
                    }
                    // 2. 保存账户订单
                    userCreditOrderDao.insert(userCreditOrderReq);
                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    log.error("调整账户积分额度异常，唯一索引冲突 userId:{} orderId:{}", userId, creditOrderEntity.getOrderId(), e);
                } catch (Exception e) {
                    status.setRollbackOnly();
                    log.error("调整账户积分额度失败 userId:{} orderId:{}", userId, creditOrderEntity.getOrderId(), e);
                }
                return 1;
            });
        } finally {
            dbRouter.clear();
            lock.unlock();
        }


    }
}
