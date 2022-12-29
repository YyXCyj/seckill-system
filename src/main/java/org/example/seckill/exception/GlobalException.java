package org.example.seckill.exception;

import org.example.seckill.vo.RespBean;
import org.example.seckill.vo.RespBeanEnum;

/**
 * 全局异常
 */


public class GlobalException extends RuntimeException {

    private RespBeanEnum respBeanEnum;

    public RespBeanEnum getRespBeanEnum() {
        return respBeanEnum;
    }

    public void setRespBeanEnum(RespBeanEnum respBeanEnum) {
        this.respBeanEnum = respBeanEnum;
    }

    public GlobalException(RespBeanEnum respBeanEnum) {
        this.respBeanEnum = respBeanEnum;
    }
}
