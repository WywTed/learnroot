package com.huhusky.microservice.zuul.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private RouteLocator locator;
	
	public void refreshRoute() {
		RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(locator);
		publisher.publishEvent(routesRefreshedEvent);
	}
	
	public void addRoute() {
		RouteInfo.routerInfo.add(new ZuulRouterBean("api-server-bb", "/providerbb/**", "provider-server-b", null, true, false, true));
		refreshRoute();
	}

}
