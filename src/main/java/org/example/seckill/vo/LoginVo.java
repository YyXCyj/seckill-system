package org.example.seckill.vo;


//import org.example.seckill.validator.IsMobile;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

//import com.sun.istack.internal.NotNull;

//import com.sun.istack.internal.NotNull;

//import com.sun.istack.internal.NotNull;

/**
 * 登录传参
 *
 */
public class LoginVo {

    @NotNull
    @org.example.seckill.validator.IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;

    @Override
    public String toString() {
        return "LoginVo{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
