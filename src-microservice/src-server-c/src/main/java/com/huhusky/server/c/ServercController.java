package com.huhusky.server.c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
public class ServercController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/binfo")
	@HystrixCommand(fallbackMethod="defaultBinfo")
	public String binfo() {
		String info = restTemplate.getForObject("http://localhost:9040/binfo", String.class);
		System.out.println(info);
		return info;
	}
	
	public String defaultBinfo(Throwable e) {
		e.printStackTrace();
		return "default binfo";
	}
	
	@GetMapping("/dinfo")
//	@HystrixCommand(fallbackMethod="defaultBinfo")
	public String dinfo() {
		String info = "default";
		info = restTemplate.getForObject("http://localhost:9060/dinfo", String.class);
		try {
			System.out.println(info);
			info = restTemplate.getForObject("http://localhost:9040/binfo", String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	
	public String defaultDinfo(Throwable e) {
		e.printStackTrace();
		return "default dinfo";
	}

}
