package com.huhusky.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
//@RabbitListener(queues = "Mq:ServiceDeleted")
public class MessageHandler3 {

//    @RabbitHandler
    public void onHello1(String context){
        System.out.println(context);
    }
}
