package cn.hzq.trigger.api;

import cn.hzq.trigger.api.dto.*;
import cn.hzq.types.model.Response;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/3/16
 * @Description 抽奖服务接口
 **/
public interface IRaffleStrategyService {
    /**
     * 策略装配接口
     *
     * @param strategyId 策略Id
     * @return 装配结果
     */
    Response<Boolean> strategyArmory(Long strategyId);

    /**
     * 查询抽奖奖品列表配置
     *
     * @param requestDTO 抽奖奖品列表查询请求参数
     * @return 奖品列表数据
     */
    Response<List<RaffleAwardListResponseDTO>> queryRaffleAwardList(RaffleAwardListRequestDTO requestDTO);

    /**
     * 随机抽奖接口
     *
     * @param requestDTO 请求参数
     * @return 抽奖结果
     */
    Response<RaffleStrategyResponseDTO> randomRaffle(RaffleStrategyRequestDTO requestDTO);

    /**
     * 通过活动Id查询权重配置信息
     *
     * @param request 请求参数
     * @return 权重值以及奖品信息
     */
    Response<List<RaffleStrategyRuleWeightResponseDTO>> queryRaffleStrategyRuleWeight(RaffleStrategyRuleWeightRequestDTO request);

}
