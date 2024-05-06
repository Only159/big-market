package cn.hzq.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/3/13
 * @Description 抽奖奖品实体
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardEntity {


    /**
     * 奖品ID
     */
    private Integer awardId;
    /**
     * 奖品标题
     */
    private String awardTitle;

    /**
     * 奖品配置信息
     */
    private String awardConfig;
    /**
     * 抽奖顺序号
     */
    private Integer sort;


}
