package com.huhusky.microservice.zuul.route;

import java.util.ArrayList;
import java.util.List;
class RouteInfo {

	public static List<ZuulRouterBean> routerInfo = new ArrayList<>();
	
	static {
		routerInfo.add(new ZuulRouterBean("api-server-b", "/providerb/**", "provider-server-b", null, true, false, true));
	}
	
}
