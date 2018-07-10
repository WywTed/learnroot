package com.huhusky.server.d;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableCircuitBreaker
@EnableFeignClients
@EnableEurekaClient

@ServletComponentScan

@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
public class ServerDApplication 
{
    public static void main( String[] args ) {
    	SpringApplication.run(ServerDApplication.class, args);
    	
    }
    
    @Bean
  RestTemplate restTemplate() {
  	return new RestTemplate();
  }
  
    
}
