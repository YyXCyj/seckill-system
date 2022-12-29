package org.example.seckill.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author yy
 * @version 1.0
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue(){
        return new Queue("queue",true);
    }
}
