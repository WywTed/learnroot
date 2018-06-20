package com.huhusky.microservice.server.b.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.prometheus.client.Counter;

@RestController
public class SimpleController {
	
	@GetMapping("/binfo")
	public String binfo() {
		return "provider-server-b";
	}

	@RequestMapping("/infob")
	public String info() {
		return "provider-server-b";
	}

	@RequestMapping("/infopath/{name}")
	public String infopath(@PathVariable String name) {
		return name;
	}

	private static Random random = new Random();

	private static final Counter requestTotal = Counter.build()
			.name("my_sample_counter")
			.labelNames("status")
			.help("A simple Counter to illustrate custom Counters in Spring Boot and Prometheus").register();

	@RequestMapping("/endpointA")
	public void endpoint() {
		if (random.nextInt(2) > 0) {
			requestTotal.labels("success").inc();
		} else {
			requestTotal.labels("error").inc();
		}
	}

}
