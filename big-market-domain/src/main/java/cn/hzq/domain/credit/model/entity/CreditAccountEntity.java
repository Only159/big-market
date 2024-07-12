package cn.hzq.domain.credit.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-11
 * @Description: 积分账户实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditAccountEntity {
    /** 用户ID */
    private String userId;
    /** 可用积分，每次扣减的值 */
    private BigDecimal adjustAmount;

}
