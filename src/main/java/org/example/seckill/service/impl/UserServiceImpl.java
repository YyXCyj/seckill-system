package org.example.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.seckill.exception.GlobalException;
import org.example.seckill.mapper.UserMapper;
import org.example.seckill.pojo.User;
import org.example.seckill.service.IUserService;
import org.example.seckill.utils.CookieUtil;
import org.example.seckill.utils.MD5Util;
import org.example.seckill.utils.UUIDUtil;
import org.example.seckill.utils.ValidatorUtil;
import org.example.seckill.vo.LoginVo;
import org.example.seckill.vo.RespBean;
import org.example.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yy
 * @version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespBean doLongin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {

        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
//        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }
//        if(!ValidatorUtil.isMobile(mobile)){
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//        }
        User user = userMapper.selectById(mobile);
        if(user==null){
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
//        String a =MD5Util.inputPassToDBPass(password,user.getSalt());
//        String b= user.getPassword();
//        System.out.println(a==b);
        if(!MD5Util.formPassToDBPass(password,user.getSalt()).equals(user.getPassword())){
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        String ticket= UUIDUtil.uuid();
//        request.getSession().setAttribute(ticket,user);
        redisTemplate.opsForValue().set("user:"+ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return RespBean.success();
    }

    @Override
    public User getUserTicket(String userTicket,HttpServletRequest request, HttpServletResponse response) {
        if(StringUtils.isEmpty(userTicket)){
            return null;
        }
        User user = (User)redisTemplate.opsForValue().get("user:" + userTicket);
        if(user!=null){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }

    @Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
