package cn.hzq.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 活动账户（月）实体对象
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityAccountMonthEntity {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 月（yyyy-mm）
     */
    private String month;
    /**
     * 月次数
     */
    private Integer monthCount;
    /**
     * 月次数-剩余
     */
    private Integer monthCountSurplus;

    public String currentMonth() {
        return dateFormat.format(new Date());
    }

}

