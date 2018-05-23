package com.huhusky.wechat;

import java.nio.charset.Charset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WechatApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WechatApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();  
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return new RestTemplate();
	}
	
}
