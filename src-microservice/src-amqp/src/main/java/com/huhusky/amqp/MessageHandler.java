package com.huhusky.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {

    @RabbitHandler
    @RabbitListener(queues = "hello")
    public void onReceiveServiceDeletedMessage(String context){
        System.out.println(context);
    }

    /*@RabbitListener(queues = "sleuth.sleuth")
    @RabbitHandler
    public void onHello(String context){
        System.out.println(context);
    }

    @RabbitListener(queues = "Mq:ServiceDeleted")
    @RabbitHandler
    public void onHello1(String context){
        System.out.println(context);
    }*/
}
