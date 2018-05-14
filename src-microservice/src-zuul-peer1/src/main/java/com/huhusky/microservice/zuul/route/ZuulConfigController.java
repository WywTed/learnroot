package com.huhusky.microservice.zuul.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zuulConfig")
public class ZuulConfigController {
	
	@Autowired
	private RouteService routeService;
	
	@RequestMapping("/reloadRoute")
	public void reloadRoute() {
		routeService.addRoute();
	}
	
	

}
