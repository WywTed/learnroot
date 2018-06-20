package com.huhusky.microservice.server.b.configure;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserMetrics;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolMetrics;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisherCollapser;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisherCommand;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisherThreadPool;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyHystrixMetricsPublisher extends HystrixMetricsPublisher{
	
	@Override
	public HystrixMetricsPublisherCollapser getMetricsPublisherForCollapser(HystrixCollapserKey collapserKey,
			HystrixCollapserMetrics metrics, HystrixCollapserProperties properties) {
		log.info("collapserKey: {}", JSON.toJSONString(collapserKey));
		log.info("metrics: {}", JSON.toJSONString(metrics));
		log.info("properties: {}", JSON.toJSONString(properties));
		return super.getMetricsPublisherForCollapser(collapserKey, metrics, properties);
	}
	
	@Override
	public HystrixMetricsPublisherCommand getMetricsPublisherForCommand(HystrixCommandKey commandKey,
			HystrixCommandGroupKey commandGroupKey, HystrixCommandMetrics metrics, HystrixCircuitBreaker circuitBreaker,
			HystrixCommandProperties properties) {
		
		log.info("commandKey: {}", JSON.toJSONString(commandKey));
		log.info("commandGroupKey: {}", JSON.toJSONString(commandGroupKey));
		log.info("metrics: {}", JSON.toJSONString(metrics));
		log.info("circuitBreaker: {}", JSON.toJSONString(circuitBreaker));
		log.info("properties: {}", JSON.toJSONString(properties));
		return super.getMetricsPublisherForCommand(commandKey, commandGroupKey, metrics, circuitBreaker, properties);
	}
	
	@Override
	public HystrixMetricsPublisherThreadPool getMetricsPublisherForThreadPool(HystrixThreadPoolKey threadPoolKey,
			HystrixThreadPoolMetrics metrics, HystrixThreadPoolProperties properties) {
		
		log.info("threadPoolKey: {}", JSON.toJSONString(threadPoolKey));
		log.info("metrics: {}", JSON.toJSONString(metrics));
		log.info("properties: {}", JSON.toJSONString(properties));
		
		return super.getMetricsPublisherForThreadPool(threadPoolKey, metrics, properties);
	}
	
	private static MyHystrixMetricsPublisher instance = new MyHystrixMetricsPublisher();
	
	public static MyHystrixMetricsPublisher getInstance() {
		return instance;
	}

}
