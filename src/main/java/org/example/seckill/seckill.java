package org.example.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yy
 * @version 1.0
 */
@SpringBootApplication
@MapperScan("org.example.seckill.mapper")
public class seckill {
    public static void main(String[] args) {
        SpringApplication.run(seckill.class,args);
    }
}
