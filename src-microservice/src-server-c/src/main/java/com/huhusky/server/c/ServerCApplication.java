package com.huhusky.server.c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableCircuitBreaker
@EnableEurekaClient
@ServletComponentScan
public class ServerCApplication 
{
    public static void main( String[] args ) {
    	SpringApplication.run(ServerCApplication.class, args);
    }
    
    @Bean
//    @LoadBalanced
    RestTemplate restTemplate() {
    	return new RestTemplate();
    }
    
}
