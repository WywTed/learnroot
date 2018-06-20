package com.huhusky.microservice.server.b.configure;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.netflix.hystrix.strategy.HystrixPlugins;

@WebListener
public class AppStartListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {

//		HystrixPlugins.getInstance().registerEventNotifier(HystrixEventListener.getInstance());
//		HystrixPlugins.getInstance().registerMetricsPublisher(MyHystrixMetricsPublisher.getInstance());
		
//		HystrixPrometheusMetricsPublisher.register();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	
	

}
