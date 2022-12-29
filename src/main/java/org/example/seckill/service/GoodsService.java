package org.example.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.seckill.pojo.Goods;
import org.example.seckill.vo.GoodsVo;

import java.util.List;

/**
 * @author yy
 * @version 1.0
 */
public interface GoodsService extends IService<Goods> {

    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoById(Long goodsId);
}
