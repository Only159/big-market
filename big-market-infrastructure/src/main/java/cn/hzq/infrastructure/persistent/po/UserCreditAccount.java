package cn.hzq.infrastructure.persistent.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 黄照权
 * @Date 2024/7/2
 * @Description 用户积分账户
 **/
@Data
public class UserCreditAccount {
    /**
     * 自增Id
     */
    private String id;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 总积分，显示总账户值，记录一个人获得的总积分
     */
    private BigDecimal totalAmount;
    /**
     * 可用积分，每次扣减的值
     */
    private BigDecimal availableAmount;
    /**
     * 用户状态【open ->可用，close ->冻结】
     */
    private String accountStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
