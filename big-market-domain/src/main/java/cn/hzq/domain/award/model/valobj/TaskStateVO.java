package cn.hzq.domain.award.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 任务状态枚举值
 **/
@Getter
@AllArgsConstructor
public enum TaskStateVO {
    create("create", "创建"),

    complete("complete", "发送完成"),

    fail("fail", "发送失败");

    private final String code;
    private final String info;
}
