package com.huhusky.microservice.zipkinclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableFeignClients //引入feign支持
//@EnableAutoConfiguration //引入自动配置，替代配置文件
public class ZipkinClientApplication {
	public static void main(String[] args) {
        SpringApplication.run(ZipkinClientApplication.class,args);
    }
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
