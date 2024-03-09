package cn.hzq.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author 黄照权
 * @Date 2024/3/10
 * @Description 抽奖策略
 **/
@Data
public class Strategy {
    /**自增ID*/
    private Long id;
    /**抽奖策略ID*/
    private Long strategyId;
    /**抽奖策略描述*/
    private String strategyDesc;
    /**策略模型*/
    private String ruleModels;
    /**创建时间*/
    private Date createTime;
    /**更新时间*/
    private Date updateTime;
}
