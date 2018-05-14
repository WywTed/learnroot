package com.huhusky.microservice.server.b.feign;

import org.springframework.stereotype.Component;

@Component
public class AServerHystrixFallback implements AServer{

	@Override
	public String infoa() {
		return "provider-server-a hystrix:error";
	}

}
