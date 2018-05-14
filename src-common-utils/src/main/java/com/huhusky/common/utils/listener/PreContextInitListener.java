package com.huhusky.common.utils.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public abstract class PreContextInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Initial();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		Destroy();
	}

	public abstract void Initial();

	public abstract void Destroy();

}
