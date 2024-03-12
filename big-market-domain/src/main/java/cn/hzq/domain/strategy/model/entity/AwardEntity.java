package cn.hzq.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/3/13
 * @Description 策略结果实体
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AwardEntity {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 奖品ID
     */
    private Integer awardId;

}