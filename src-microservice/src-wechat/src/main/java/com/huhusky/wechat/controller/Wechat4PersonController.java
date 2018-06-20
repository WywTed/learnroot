package com.huhusky.wechat.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.huhusky.wechat.cons.WechatConsts;
import com.huhusky.wechat.msg.SendMsgReqbody;
import com.huhusky.wechat.msg.WechatMsgConfig;
import com.huhusky.wechat.service.DefaultApiDataHandler;
import com.huhusky.wechat.service.Msgservice;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.api.Core;
import cn.zhouyafeng.itchat4j.api.MessageTools;
import cn.zhouyafeng.itchat4j.api.WechatTools;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;

@RestController
@RequestMapping("/wechat4person")
public class Wechat4PersonController {

	@Autowired
	private IMsgHandlerFace defaultMsgHandler;
	@Autowired
	private DefaultApiDataHandler apiDataHandler;
	
	
	@Autowired
	private Msgservice msgService;
	
	
	@RequestMapping("/login")
	public void login() {
//		IMsgHandlerFace msgHandler = new SimpleDemo(); // 实现IMsgHandlerFace接口的类
		Wechat wechat = new Wechat(apiDataHandler, defaultMsgHandler, WechatConsts.QrcodeStoragePath); // 【注入】
		wechat.start(); // 启动服务，会在qrPath下生成一张二维码图片，扫描即可登陆，注意，二维码图片如果超过一定时间未扫描会过期，过期时会自动更新，所以你可能需要重新打开图片
	}
	
	@GetMapping("/core")
	public Core getCore() {
		return Core.getInstance();
	}
	
	@PutMapping("/msg/config/refresh")
	public void refreshMsgConfig() {
		msgService.refreshMsgConfig();
	}
	
	@PutMapping("/msg/configurl/refresh")
	public void refreshConfigUrl(@RequestBody JSONObject payload) {
		if(payload == null || StringUtils.isBlank(payload.getString("configUrl"))) {
			return ;
		}
		WechatConsts.remoteConfigUrl = payload.getString("configUrl");
		msgService.refreshMsgConfig();
	}
	
	@PutMapping("/msg/resolveurl/refresh")
	public void refreshMesResolveUrl(@RequestBody JSONObject payload) {
		if(payload == null || StringUtils.isBlank(payload.getString("msgResolveUrl"))) {
			return ;
		}
		WechatConsts.remoteResolveMsgUrl = payload.getString("msgResolveUrl");
	}
	
	@PostMapping("/sendmsg")
	public String sendMsg(@RequestBody SendMsgReqbody msgReqbody) {
		if(msgReqbody == null || StringUtils.isBlank(msgReqbody.getMsgType())) {
			return "illeagal parameter";
		}
		String msgType = msgReqbody.getMsgType();
		String toUserName = msgReqbody.getToUserName();
		// 回复 text 类型消息
		if(WechatMsgConfig.Rettype_Text.equals(msgType)){
			MessageTools.sendMsg(msgReqbody.getContent(), toUserName);
		}
		
		//回复文件类型消息
		if(WechatMsgConfig.Rettype_File.equals(msgType)) {
			MessageTools.sendFileMsgByUserId(toUserName, msgReqbody.getFilePath());
		}
		
		// 回复图片消息
		if(WechatMsgConfig.Rettype_Pic.equals(msgType)) {
			MessageTools.sendPicMsgByUserId(toUserName, msgReqbody.getFilePath());
		}
		
		return "success";
	}
	
	@PutMapping("/switcher/{msgType}/{switcher}")
	public String updateSwitcher(@PathVariable String msgType, @PathVariable boolean switcher) {
		WechatMsgConfig.MsgResolveSwitcher.put(msgType, switcher);
		return "success";
	}
	
	@GetMapping("/switchers")
	public Map<String, Boolean> getSwitcher(){
		return WechatMsgConfig.MsgResolveSwitcher;
	}
	
	@DeleteMapping("/switcher/{msgType}")
	public String deleteSwitcher(@PathVariable String msgType) {
		Boolean switcher = WechatMsgConfig.MsgResolveSwitcher.get(msgType);
		if(switcher == null) {
			return "msgType not exist";
		}
		return WechatMsgConfig.MsgResolveSwitcher.remove(msgType) + "";
	}
	
	@PutMapping("/logout")
	public void logout() {
		WechatTools.logout();
	}
}
