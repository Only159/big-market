package cn.hzq.domain.credit.model.aggregate;

import cn.hzq.domain.credit.model.entity.CreditAccountEntity;
import cn.hzq.domain.credit.model.entity.CreditOrderEntity;
import cn.hzq.domain.credit.model.valobj.TradeNameVO;
import cn.hzq.domain.credit.model.valobj.TradeTypeVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-11
 * @Description: 交易聚合对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeAggregate {
    // 用户ID
    private String userId;
    // 积分账户实体
    private CreditAccountEntity creditAccountEntity;
    // 积分订单实体
    private CreditOrderEntity creditOrderEntity;

    // 创建积分账户实体
    public static CreditAccountEntity createCreditAccountEntity(String userId, BigDecimal adjustAmount) {
        return CreditAccountEntity.builder().userId(userId).adjustAmount(adjustAmount).build();
    }

    // 创建积分订单实体
    public static CreditOrderEntity createCreditOrderEntity(String userId,
                                                            TradeNameVO tradeName,
                                                            TradeTypeVO tradeType,
                                                            BigDecimal tradeAmount,
                                                            String outBusinessNo) {
        return CreditOrderEntity.builder()
                .userId(userId)
                .orderId(RandomStringUtils.randomNumeric(12))
                .tradeName(tradeName)
                .tradeType(tradeType)
                .tradeAmount(tradeAmount)
                .outBusinessNo(outBusinessNo)
                .build();
    }


}
