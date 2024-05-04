package cn.hzq.types.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 黄照权
 * @Date 2024/5/4
 * @Description 基础消息模板
 **/
public abstract class BaseEvent<T> {
    public abstract EventMessage<T> buildEventMessage(T date);

    public abstract String topic();

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EventMessage<T> {
        public String id;
        public Date timestamp;
        public T data;
    }
}
