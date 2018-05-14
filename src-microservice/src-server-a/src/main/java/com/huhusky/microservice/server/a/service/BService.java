package com.huhusky.microservice.server.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huhusky.microservice.server.a.feign.BServer;

@Service
public class BService {
	
	@Autowired
	private BServer bServer ;
	
	public String infob() {
		return bServer.infob();
	}

}
