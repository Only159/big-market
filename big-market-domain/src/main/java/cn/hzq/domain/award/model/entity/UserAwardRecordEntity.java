package cn.hzq.domain.award.model.entity;

import cn.hzq.domain.award.model.valobj.AwardStateVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 用户中奖记录实体对象
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAwardRecordEntity {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 抽奖策略ID
     */
    private Long strategyId;
    /**
     * 抽奖订单ID【作为幂等使用】
     */
    private String orderId;
    /**
     * 奖品ID
     */
    private Integer awardId;
    /**
     * 奖品标题（名称）
     */
    private String awardTitle;
    /**
     * 中奖时间
     */
    private Date awardTime;
    /**
     * 奖品状态；create-创建、completed-发奖完成
     */
    private AwardStateVO awardState;
    /**
     * 奖品配置信息；发奖的时候，可以根据
     */
    private String awardConfig;

}
