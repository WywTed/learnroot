package com.huhusky.microservice.server.a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huhusky.microservice.server.a.service.BService;

@RestController
@RequestMapping("/bserver")
public class BController {
	
	@Autowired
	private BService bService;

	@RequestMapping("/infob")
	public String infob() {
		return bService.infob();
	}
	
	@RequestMapping("/bexception")
	public String throwException() {
		throw new RuntimeException();
	}
}
