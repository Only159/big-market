package cn.hzq.domain.award.model.aggregate;

import cn.hzq.domain.award.model.entity.TaskEntity;
import cn.hzq.domain.award.model.entity.UserAwardRecordEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/5/5
 * @Description 用户中奖记录聚合对象【一个聚合代表一个事务】
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAwardRecordAggregate {
    private UserAwardRecordEntity userAwardRecordEntity;
    private TaskEntity taskEntity;
}
