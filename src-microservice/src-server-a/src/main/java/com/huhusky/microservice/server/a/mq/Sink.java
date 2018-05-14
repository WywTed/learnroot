package com.huhusky.microservice.server.a.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Sink {
	
	String INPUT_1 = "testa";
	
//	@Input(Sink.INPUT_1)
	SubscribableChannel input1();;

}
