package com.huhusky.microservice.server.b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huhusky.microservice.server.b.service.AService;

@RestController
@RequestMapping("/aserver")
public class AController {
	
	@Autowired
	private AService aService;
	
	@RequestMapping("/infoa")
	public String infoa() {
		return aService.infoa();
	}
	
	@RequestMapping("/aexception")
	public String throwException() {
		throw new RuntimeException();
	}
	
	@RequestMapping("/testok")
	public String testOK() {
		return "ok";
	}
	
	@PostMapping
	public String postServer() {
		return "post";
	}
	
	@GetMapping String getServer() {
		return "get";
	}
	
}
