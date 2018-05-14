package com.huhusky.microservice.server.b.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
	
	@RequestMapping("/infob")
	public String info() {
		return "provider-server-b";
	}
	
	@RequestMapping("/infopath/{name}")
	public String infopath(@PathVariable String name) {
		return name;
	}

}
