package com.huhusky.wechat.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.huhusky.wechat.service.Msgservice;
import com.huhusky.wechat.service.WechatQueueService;

import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class WechatStarterListener implements ServletContextListener{
	
	
	@Autowired
	private Msgservice msgservice;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("###########  启用 队列监听，等待通知 ###########");
		new Thread(() -> {
			WechatQueueService.exec();
		}).start();
		
		log.info("##########  远程加载回复类型配置  ############ ");
		msgservice.refreshMsgConfig();
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
