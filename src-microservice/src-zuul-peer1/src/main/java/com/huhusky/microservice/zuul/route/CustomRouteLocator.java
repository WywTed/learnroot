package com.huhusky.microservice.zuul.route;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.util.StringUtils;


public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator{

	private static Logger logger = LoggerFactory.getLogger(CustomRouteLocator.class);

//	private JdbcTemplate jdbcTemplate;

	private ZuulProperties properties;

	public CustomRouteLocator(String servletPath, ZuulProperties properties) {
		super(servletPath, properties);
		this.properties = properties;
		logger.info("server info: {}", servletPath);
	}

	/*public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}*/

	@Override
	public void refresh() {
		doRefresh();
	}

	@Override
	protected Map<String, ZuulRoute> locateRoutes() {
		LinkedHashMap<String, ZuulRoute> routeMap = new LinkedHashMap<>();
		routeMap.putAll(super.locateRoutes());
		Map<String, ZuulRoute> dbRoute = locateRouteFromDb();
		if(!dbRoute.isEmpty()) {
			routeMap.putAll(locateRouteFromDb());
		}
		// 优化
		LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
		for(Map.Entry<String, ZuulRoute> entry : routeMap.entrySet()) {
			String path = entry.getKey();
			if(path == null) {
				continue;
			}
			if(!path.startsWith("/")) {
				path = "/" + path;
			}
			if(StringUtils.hasText(this.properties.getPrefix())) {
				path = this.properties.getPrefix() + path;
				if(!path.startsWith("/")) {
					path = "/" + path;
				}
			}
			values.put(path, entry.getValue());
		}
		System.out.println("route-infos: " + values);
		return values;
	}

	private Map<String, ZuulRoute> locateRouteFromDb() {
		logger.info("use jdbcTemplate to get routes-info from db. 假裝從數據庫中取得數據");
		LinkedHashMap<String, ZuulRoute> routeMap = new LinkedHashMap<>();
		if(!RouteInfo.routerInfo.isEmpty()) {
			for(ZuulRouterBean zrBean : RouteInfo.routerInfo) {
//				if(StringUtils.isBlank(zrBean.getPath()) || StringUtils.isBlank(zrBean.getUrl()))
				ZuulRoute zr = new ZuulRoute();
				BeanUtils.copyProperties(zrBean, zr);
				routeMap.put(zr.getPath(), zr);
			}
		}
		return routeMap;
	}



}
