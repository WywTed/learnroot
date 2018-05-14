package com.huhusky.microservice.zuul.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomZuulConfiguration {
	
	@Autowired
	ZuulProperties zuulProperties;


	@Autowired
	ServerProperties serverProperties;
	
//	@Autowired
//	JdbcTemplate jdbcTemplate;
	
	@Bean
	public CustomRouteLocator routeLocator() {
		CustomRouteLocator crl = new CustomRouteLocator(serverProperties.getServletPrefix(), zuulProperties);
//		crl.setJdbcTemplate(jdbcTemplate);
		return crl;
		
	}

}
