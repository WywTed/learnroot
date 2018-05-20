package com.huhusky.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huhusky.wechat.cons.WechatConsts;
import com.huhusky.wechat.service.DefaultApiDataHandler;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.service.ILoginService;
import cn.zhouyafeng.itchat4j.service.impl.LoginServiceImpl;

@RestController
@RequestMapping("/wechat4person")
public class Wechat4PersonController {

	@Autowired
	private IMsgHandlerFace defaultMsgHandler;
	@Autowired
	private DefaultApiDataHandler apiDataHandler;
	
	
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
	
	
}
