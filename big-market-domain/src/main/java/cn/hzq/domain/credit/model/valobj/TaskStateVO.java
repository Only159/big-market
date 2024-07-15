package cn.hzq.domain.credit.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 黄照权
 * @CreateTime: 2024-07-15
 * @Description: 任务状态枚举值对象
 */

@Getter
@AllArgsConstructor
public enum TaskStateVO {
    create("create", "创建"),
    complete("complete", "发送完成"),
    fail("fail", "发送失败");
    private final String code;
    private final String info;

}