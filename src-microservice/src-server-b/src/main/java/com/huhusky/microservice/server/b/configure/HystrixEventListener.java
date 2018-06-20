package com.huhusky.microservice.server.b.configure;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import com.netflix.hystrix.HystrixEventType;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HystrixEventListener extends HystrixEventNotifier{
	
	private static HystrixEventListener instance = new HystrixEventListener(); 
	
	public static HystrixEventListener getInstance() {
		return instance;
	}
	
	@Override
	public void markCommandExecution(HystrixCommandKey key, ExecutionIsolationStrategy isolationStrategy, int duration,
			List<HystrixEventType> eventsDuringExecution) {
		super.markCommandExecution(key, isolationStrategy, duration, eventsDuringExecution);
		
		log.info("key: {}", JSON.toJSONString(key));
		log.info("isolationStrategy: {}", JSON.toJSONString(isolationStrategy));
		log.info("duration: {}", duration);
		log.info("eventsDuringExecution: {}", JSON.toJSONString(eventsDuringExecution));
	}
	
	@Override
	public void markEvent(HystrixEventType eventType, HystrixCommandKey key) {
		
		log.info("eventType: {}", JSON.toJSONString(eventType));
		log.info("key: {}", JSON.toJSONString(key));
	}


}
