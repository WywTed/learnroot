package com.huhusky.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.huhusky.wechat.cons.WechatConsts;
import com.huhusky.wechat.msg.WechatMsgConfig;
import com.huhusky.wechat.msg.WechatMsgConfig.Msgconfig;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Msgservice {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void refreshMsgConfig() {
		log.info("####  加载远程消息回复配置信息  #####");
		WechatMsgConfig.TextmsgConfig = restTemplate.getForObject(WechatConsts.remoteConfigUrl, Msgconfig.class);
		log.info("####  远程配置信息： \n" + WechatMsgConfig.TextmsgConfig);
	}

}
	