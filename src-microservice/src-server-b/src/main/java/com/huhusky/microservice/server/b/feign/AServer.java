package com.huhusky.microservice.server.b.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="provider-server-a", fallback=AServerHystrixFallback.class)
public interface AServer {

	
	@RequestMapping("/infoa")
	String infoa();
	
}
