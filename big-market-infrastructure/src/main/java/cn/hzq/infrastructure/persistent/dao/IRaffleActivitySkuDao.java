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
    RaffleActivitySku queryRaffleActivitySkuBySku(Long sku);
}
