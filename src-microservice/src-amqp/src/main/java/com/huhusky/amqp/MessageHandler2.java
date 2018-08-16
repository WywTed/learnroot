package com.huhusky.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
//@RabbitListener(queues = "sleuth.sleuth")
public class MessageHandler2 {


//    @RabbitHandler
    public void onHello(String context){
        System.out.println(context);
    }

}
