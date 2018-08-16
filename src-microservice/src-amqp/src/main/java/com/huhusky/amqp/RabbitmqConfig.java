package com.huhusky.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfig {
	
	public static final String Msg_Hello = "hello";
	public static final String Msg_Sleuth = "sleuth.sleuth";
	public static final String Msg_Sev = "Mq:ServiceDeleted";
	
	@Bean
	public Queue queue() {
		return new Queue(Msg_Hello); //Msg_Hello
	}
	/*@Bean
	public Queue sevQueue() {
		return new Queue(Msg_Sev);
	}*/
	
	//直连交换机
  /*  @Bean
    public TopicExchange defaultExchange() {
        return new TopicExchange(Msg_Sleuth); 
    }*/


    //绑定
    /*@Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Msg_Hello);
    }*/
    
   /* @Bean
    public AmqpTemplate amqpTemplate() {
    	return new AmqpTemplate();
    }*/
    
    /*@Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(Msg_Hello);
//        container.setMessageListener(listenerAdapter);
        return container;
    }*/

    /*@Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }*/

}
