package com.huhusky.wechat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RestController
@Slf4j
public class WechatApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WechatApplication.class, args);
	}
	
	
	@Autowired
	private TestMapper testMapper;
	
	@RequestMapping("/hello")
	public String hello() {
		log.info("hello world");
		List<Scope> scopes = testMapper.getAll();
		log.info(scopes.toString());
		return "hellow world";
	}

}
