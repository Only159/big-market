package cn.hzq.domain.activity.service;

import cn.hzq.domain.activity.repository.IActivityRepository;
import org.springframework.stereotype.Service;

/**
 * @author 黄照权
 * @Date 2024/4/24
 * @Description 抽奖活动服务
 **/
@Service
public class RaffleActivityService extends AbstractRaffleActivity {
    public RaffleActivityService(IActivityRepository activityRepository) {
        super(activityRepository);
    }
}
