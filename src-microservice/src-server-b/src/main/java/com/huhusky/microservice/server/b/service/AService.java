package com.huhusky.microservice.server.b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huhusky.microservice.server.b.feign.AServer;

@Service
public class AService {
	
	@Autowired
	private AServer aServer;
	
	public String infoa() {
		return aServer.infoa();
		
	}

}
