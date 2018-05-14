package com.huhusky.microservice.server.a.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

	//	@Autowired
	//	private KafkaSender sender;

	@RequestMapping("/infoa")
	public String info() {
		//		sender.sendMessage("just test message ");
		return "provider-server-a";
	}


	@Value("${servera.hello}")
	private String hello;

	@RequestMapping("/hello")
	public String from() {
		return this.hello;
	}
}
