package cn.hzq.trigger.api;

import cn.hzq.trigger.api.dto.ActivityDrawRequestDTO;
import cn.hzq.trigger.api.dto.ActivityDrawResponseDTO;
import cn.hzq.types.model.Response;

/**
 * @author 黄照权
 * @Date 2024/5/6
 * @Description 抽奖活动服务
 **/
public interface IRaffleActivityService {
    /**
     * 活动装配，数据预热到缓存
     *
     * @param activityId 活动Id
     * @return 装配结果
     */
    Response<Boolean> armory(Long activityId);

    /**
     * 活动抽奖接口
     *
     * @param requestDTO 请求对象
     * @return 返回结果
     */
    Response<ActivityDrawResponseDTO> draw(ActivityDrawRequestDTO requestDTO);

    /**
     * 日历签到返利接口
     *
     * @param userId 用户Id
     * @return 签到结果
     */
    Response<Boolean> calendarSingRebate(String userId);
}
