package cn.hzq.domain.activity.model.entity;

import cn.hzq.domain.activity.model.valobj.UserRaffleOrderStateVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 用户抽奖订单实体对象
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRaffleOrderEntity {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 抽奖策略ID
     */
    private Long strategyId;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 下单时间
     */
    private Date orderTime;
    /**
     * 订单状态；create-创建、used-已使用、cancel-已作废
     */
    private UserRaffleOrderStateVO orderState;
}
