package org.example.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.seckill.pojo.Order;
import org.example.seckill.pojo.User;
import org.example.seckill.vo.GoodsVo;

/**
 * @author yy
 * @version 1.0
 */
public interface OrderMapper extends BaseMapper<Order> {
    Order seckill(User user, GoodsVo goods);
}
