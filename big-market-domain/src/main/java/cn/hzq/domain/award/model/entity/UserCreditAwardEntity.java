package cn.hzq.domain.award.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 黄照权
 * @description 用户积分奖品实体对象
 * @create 2024-05-18 09:15
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreditAwardEntity {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 积分值
     */
    private BigDecimal creditAmount;

}

