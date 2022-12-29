package org.example.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.seckill.mapper.SeckillOrderMapper;
import org.example.seckill.pojo.SeckillOrder;
import org.example.seckill.service.SeckillOrderService;
import org.springframework.stereotype.Service;

/**
 * @author yy
 * @version 1.0
 */
@Service
public class SeckillOrderServiceImpl  extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements SeckillOrderService {
}
