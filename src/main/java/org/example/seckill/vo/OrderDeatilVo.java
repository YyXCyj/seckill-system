package org.example.seckill.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.seckill.pojo.Order;
import org.example.seckill.vo.GoodsVo;

/**
 * 订单详情返回对象
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDeatilVo {

    private Order order;

    private GoodsVo goodsVo;
}
