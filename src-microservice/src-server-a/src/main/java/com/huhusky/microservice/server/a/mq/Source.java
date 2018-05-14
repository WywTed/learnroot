package com.huhusky.microservice.server.a.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Source {

	
	String OUTPUT_1 = "sourceA";
	
//	@Output(Source.OUTPUT_1)
	MessageChannel output1();
}
