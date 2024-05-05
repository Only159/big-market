package cn.hzq.domain.award.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 奖品状态枚举值对象
 **/
@Getter
@AllArgsConstructor
public enum AwardStateVO {
    create("create", "创建"),
    complete("complete", "发奖完成"),
    fail("fail", "发奖失败");

    private final String code;
    private final String info;

}
