package org.example.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.example.seckill.mapper.OrderMapper;
import org.example.seckill.pojo.Order;
import org.example.seckill.pojo.SeckillGoods;
import org.example.seckill.pojo.SeckillOrder;
import org.example.seckill.pojo.User;
import org.example.seckill.service.GoodsService;
import org.example.seckill.service.OrderService;
import org.example.seckill.service.SeckillGoodsService;
import org.example.seckill.service.SeckillOrderService;
import org.example.seckill.vo.GoodsVo;
import org.example.seckill.vo.OrderDeatilVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author yy
 * @version 1.0
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SeckillOrderService seckillOrderService;

    @Autowired
    private GoodsService goodsService;

    @Override
    public Order seckill(User user, GoodsVo goods) {

        SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>().eq("goods_id", goods.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount()-1);

        seckillGoodsService.updateById(seckillGoods);

        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setPayDate(new Date());

        orderMapper.insert(order);


        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goods.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setUserId(user.getId());
        seckillOrderService.save(seckillOrder);



        return  order;
    }

    @Override
    public OrderDeatilVo detail(Long orderId) {
        Order order = orderMapper.selectById(orderId);
        GoodsVo goodsVo = goodsService.findGoodsVoById(order.getGoodsId());
        OrderDeatilVo orderDeatilVo = new OrderDeatilVo();
        orderDeatilVo.setGoodsVo(goodsVo);
        orderDeatilVo.setOrder(order);
        return orderDeatilVo;
    }
}
