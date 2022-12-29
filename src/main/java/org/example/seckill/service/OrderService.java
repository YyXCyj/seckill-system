package org.example.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.seckill.pojo.Order;
import org.example.seckill.pojo.User;
import org.example.seckill.vo.GoodsVo;
import org.example.seckill.vo.OrderDeatilVo;

/**
 * @author yy
 * @version 1.0
 */
public interface OrderService extends IService<Order> {
    Order seckill(User user, GoodsVo goods);

    OrderDeatilVo detail(Long orderId);
}
