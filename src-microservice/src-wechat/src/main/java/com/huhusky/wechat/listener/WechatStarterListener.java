package com.huhusky.wechat.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.huhusky.wechat.service.WechatQueueService;

import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class WechatStarterListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("###########  启用 队列监听，等待通知 ###########");
		new Thread(() -> {
			WechatQueueService.exec();
		}).start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
