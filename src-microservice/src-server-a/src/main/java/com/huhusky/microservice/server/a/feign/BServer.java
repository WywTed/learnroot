package com.huhusky.microservice.server.a.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="provider-server-b", fallback=BServerHystrixFallback.class)
public interface BServer {
	
	
	@RequestMapping("/infob")
	String infob();

}
