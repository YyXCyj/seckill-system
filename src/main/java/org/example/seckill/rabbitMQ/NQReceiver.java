package org.example.seckill.rabbitMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yy
 * @version 1.0
 */
@Service
@Slf4j
public class NQReceiver {
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
    @RabbitListener(queues = "queue")
    public void receiver(Object msg){
        log.info("接受消息"+msg);
    }

}
