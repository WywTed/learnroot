package com.huhusky.wechat.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huhusky.wechat.cons.WechatConsts;
import com.huhusky.wechat.service.CoinService;
import com.huhusky.wechat.service.DefaultApiDataHandler;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.service.ILoginService;
import cn.zhouyafeng.itchat4j.service.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/wechat4person")
@Slf4j
public class Wechat4PersonController {

	@Autowired
	private IMsgHandlerFace defaultMsgHandler;
	@Autowired
	private DefaultApiDataHandler apiDataHandler;
	
	@Autowired
	private CoinService coinService;
	
	
	@RequestMapping("/login")
	public void login() {
//		IMsgHandlerFace msgHandler = new SimpleDemo(); // 实现IMsgHandlerFace接口的类
		Wechat wechat = new Wechat(apiDataHandler, defaultMsgHandler, WechatConsts.QrcodeStoragePath); // 【注入】
		wechat.start(); // 启动服务，会在qrPath下生成一张二维码图片，扫描即可登陆，注意，二维码图片如果超过一定时间未扫描会过期，过期时会自动更新，所以你可能需要重新打开图片
	}
	
	@RequestMapping("/contact")
	public void getContact() {
		ILoginService loginService = new LoginServiceImpl();
		loginService.webWxGetContact();
	}
	
	@RequestMapping("/price")
	public String coinPrice() {
		return coinService.getTfprice();
	}
	
	@RequestMapping("/test/{threadCount}/{taskCount}")
	public void test(@PathVariable int threadCount, @PathVariable int taskCount) {
		ExecutorService es = Executors.newFixedThreadPool(threadCount);
		for(int i=0;i<taskCount;i++) {
			es.submit(new Runnable() {
				@Override
				public void run() {
					long start = System.currentTimeMillis();
					coinService.getTfprice();
					long end = System.currentTimeMillis();
					log.info(String.format("###### %s 耗时： %s", Thread.currentThread().getName(), end - start));
				}
			});
		}
		
	}
	
	
	@RequestMapping("/refresh/kw")
	public void refreshKeywordMap() {
		coinService.refreshAllTextmap();
	}
	
}
