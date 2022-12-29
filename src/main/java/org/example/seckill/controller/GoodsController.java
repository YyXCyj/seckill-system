package org.example.seckill.controller;

import io.lettuce.core.dynamic.annotation.Param;
import org.example.seckill.pojo.User;
import org.example.seckill.service.GoodsService;
import org.example.seckill.service.IUserService;
import org.example.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.util.StringUtils;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author yy
 * @version 1.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @RequestMapping(value = "/toList",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toList(Model model,HttpServletRequest request, HttpServletResponse response, @CookieValue("userTicket") String ticket){//HttpServletRequest request, HttpServletResponse response, @CookieValue("userTicket") String ticket


        ValueOperations valueOperations = redisTemplate.opsForValue();
        String html = (String)valueOperations.get("goodsList");
        if(!StringUtils.isEmpty(html)){
            return html;
        }

        if(StringUtils.isEmpty(ticket)){
            return "login";
        }
//        User user = (User)session.getAttribute(ticket);
        User user = iUserService.getUserTicket(ticket, request, response);
        if(null==user){
            return "login";
        }
        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsService.findGoodsVo());

        WebContext webContext = new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodsList",webContext);
        if(!StringUtils.isEmpty(html)){
            valueOperations.set("goodsList",html,60, TimeUnit.MINUTES);
        }
        return html;
//        return "goodsList";
//        return null;

    }
    @RequestMapping(value="/toDetail/{goods.id}",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toDetail(Model model,HttpServletRequest request, HttpServletResponse response, @CookieValue("userTicket") String ticket,@PathVariable("goods.id") Long GoodsId){

        ValueOperations opsForValue = redisTemplate.opsForValue();
        String  html = (String)opsForValue.get("goodsDetail:" + GoodsId);
        if(!StringUtils.isEmpty(html)){
            return html;
        }
        if(StringUtils.isEmpty(ticket)){
            return "login";
        }

//        User user = (User)session.getAttribute(ticket);

        User user = iUserService.getUserTicket(ticket, request, response);
        if(null==user){
            return "login";
        }
//        model.addAttribute("user",user);

        model.addAttribute("user",user);
        GoodsVo goodsVo = goodsService.findGoodsVoById(GoodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date date = new Date();
        int remainSeconds=0;
        int secKillStatus=0;
        if(date.before(startDate)){
            remainSeconds=(int)(startDate.getTime()-date.getTime())/1000;
        }else if(date.after(endDate)){
            secKillStatus=2;
            remainSeconds=-1;
        }else{
            secKillStatus=1;
            remainSeconds=0;
        }
        model.addAttribute("remainSeconds",remainSeconds);
        model.addAttribute("secKillStatus",secKillStatus);
        model.addAttribute("goods",goodsVo);

        WebContext webContext = new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodsDetail",webContext);
        if(!StringUtils.isEmpty(html)){
            opsForValue.set("goodsDetail:"+GoodsId,html,60, TimeUnit.MINUTES);
        }
        return html;
//        return "goodsDetail";
    }

}


















