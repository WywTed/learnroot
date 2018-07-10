package com.huhusky.server.d;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServerdController {
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/dinfo")
	public String dinfo() {
		String info = restTemplate.getForObject("http://localhost:9040/binfo", String.class);
		System.out.println(info);
		return "dinfo->binfo";
	}
}
