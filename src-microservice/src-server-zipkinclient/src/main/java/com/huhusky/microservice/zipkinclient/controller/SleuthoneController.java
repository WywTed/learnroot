package com.huhusky.microservice.zipkinclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.huhusky.microservice.zipkinclient.feign.SleuthoneFeign;

@RestController
public class SleuthoneController {
	@Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SleuthoneFeign sleuthService;

    @RequestMapping("/restHello/{name}")
    public String restHello(@PathVariable String name) {
        return "rest " + restTemplate.getForObject("http://localhost:9040/infob/" ,String.class);
    }

    @RequestMapping("/feignHello/{name}")
    public String feignHello(@PathVariable String name) {
        return "feign " + sleuthService.sayHello(name);
    }
}
