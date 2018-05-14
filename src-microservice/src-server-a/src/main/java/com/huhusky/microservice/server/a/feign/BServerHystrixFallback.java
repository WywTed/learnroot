package com.huhusky.microservice.server.a.feign;

import org.springframework.stereotype.Component;

@Component
public class BServerHystrixFallback implements BServer{

	@Override
	public String infob() {
		return "provider-server-b:infob:error";
	}

}
