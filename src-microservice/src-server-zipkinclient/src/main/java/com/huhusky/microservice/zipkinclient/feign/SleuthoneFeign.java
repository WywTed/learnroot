package com.huhusky.microservice.zipkinclient.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "sleuthone",url = "http://localhost:9040")
public interface SleuthoneFeign {

	@RequestMapping("/infopath/{name}")
	String sayHello(@PathVariable(name = "name")String name);

}
