package cn.hzq.infrastructure.persistent.dao;

import cn.hzq.infrastructure.persistent.po.RaffleActivitySku;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 黄照权
 * @Date 2024/4/24
 * @Description 商品sku dao
 **/
@Mapper
public interface IRaffleActivitySkuDao {
    /**
     * 通过sku查询详细信息
     * @param sku 活动sku
     * @return 实体对象
     */
    RaffleActivitySku queryRaffleActivitySkuBySku(Long sku);

    /**
     * 更新sku库存信息
     * @param sku 活动sku
     */
    void updateActivitySkuStock(Long sku);

    /**
     * 清空活动库存
     * @param sku 活动sku
     */
    void clearActivitySkuStock(Long sku);
}
