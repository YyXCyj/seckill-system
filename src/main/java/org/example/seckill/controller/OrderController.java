package org.example.seckill.controller;

import org.example.seckill.pojo.User;
import org.example.seckill.service.IUserService;
import org.example.seckill.service.OrderService;
import org.example.seckill.vo.OrderDeatilVo;
import org.example.seckill.vo.RespBean;
import org.example.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yy
 * @version 1.0
 */

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/detail")
    @ResponseBody
    public RespBean detail(Long orderId, HttpServletRequest request, HttpServletResponse response,@CookieValue("userTicket") String ticket){

        if(StringUtils.isEmpty(ticket)){
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        User user = iUserService.getUserTicket(ticket, request, response);
        if(null==user){
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }

        OrderDeatilVo orderDeatilVo = orderService.detail(orderId);
        return RespBean.success(orderDeatilVo);
    }
}
