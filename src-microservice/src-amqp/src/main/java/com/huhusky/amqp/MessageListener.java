package com.huhusky.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class MessageListener {

//    @RabbitHandler
    public void onReceiveServiceDeletedMessage(String context){
        System.out.println(context);
    }

}
