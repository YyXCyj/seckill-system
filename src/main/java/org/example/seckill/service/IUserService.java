package org.example.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.seckill.pojo.User;
import org.example.seckill.vo.LoginVo;
import org.example.seckill.vo.RespBean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yy
 * @version 1.0
 */
public interface IUserService extends IService<User> {

    RespBean doLongin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    User getUserTicket(String userTicket,HttpServletRequest request, HttpServletResponse response);

    User getUserByCookie(String userTicket,HttpServletRequest request, HttpServletResponse response);
}
