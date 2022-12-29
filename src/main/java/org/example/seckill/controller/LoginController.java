package org.example.seckill.controller;

//import com.example.seckilldemo.service.ITUserService;
//import com.example.seckilldemo.vo.LoginVo;
//import com.example.seckilldemo.vo.RespBean;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.seckill.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
//import javax.validation.Valid;

/**
 * @author: LC
 * @date 2022/3/2 10:11 上午
 * @ClassName: LoginController
 */

@Controller
@RequestMapping("/login")
@Slf4j
//@Api(value = "登录", tags = "登录")
public class LoginController {


    @Autowired
    private IUserService userService;


    /**
     * 跳转登录页面
     *
     * @param
     * @return java.lang.String
     * @author LC
     * @operation add
     * @date 10:13 上午 2022/3/2
     **/
//    @ApiOperation("跳转登录页面")
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }


//    @ApiOperation("登录接口")
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public org.example.seckill.vo.RespBean doLogin(@Valid org.example.seckill.vo.LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        log.info("{}", loginVo);
        return userService.doLongin(loginVo, request, response);
    }

}
