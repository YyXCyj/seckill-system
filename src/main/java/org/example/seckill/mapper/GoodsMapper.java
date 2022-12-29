package org.example.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.seckill.pojo.Goods;
import org.example.seckill.vo.GoodsVo;

import java.util.List;

/**
 * 商品表 Mapper 接口
 *
 * @author LiChao
 * @since 2022-03-03
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoById(Long goodsId);
}
