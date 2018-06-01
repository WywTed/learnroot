package com.huhusky.wechat.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.huhusky.wechat.cons.WechatConsts;
import com.huhusky.wechat.msg.WechatMsgConfig;
import com.huhusky.wechat.msg.WechatMsgConfig.KeywordRet;

import cn.zhouyafeng.itchat4j.api.MessageTools;
import cn.zhouyafeng.itchat4j.api.dto.RecommendInfo;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.face.AbsMsgHandlerFace;
import cn.zhouyafeng.itchat4j.utils.enums.MsgTypeEnum;
import cn.zhouyafeng.itchat4j.utils.tools.DownloadTools;
import lombok.extern.slf4j.Slf4j;

@Service("defaultMsgHandler")
@Slf4j
public class DefaultMsgHandler extends AbsMsgHandlerFace{
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String textMsgHandle(BaseMsg msg) {
		WechatQueueService.pushTask(new MsgsaveCallable(msg));
		return resolveTextMsg(msg);
	}

	private String resolveTextMsg(BaseMsg msg) {
		String text = msg.getText();
		// 如果不包含关键词则直接返回 null
		if(!WechatMsgConfig.containKeyword(text)) {
			return null;
		}
		// 包含关键词则搜索关键对应的返回
		KeywordRet kr = WechatMsgConfig.getKeywordRet(text);
		if(kr == null) {
			return null;
		}

		// 回复 text 类型消息
		if(WechatMsgConfig.Rettype_Text.equals(kr.getRetType())){
			// 判断是否加载远程数据
			if(!kr.isRemote()) {
				return kr.getContent();
			}
			return restTemplate.getForObject(kr.getUrl(), String.class);
		}
		
		String fromUserName = msg.getFromUserName();
		
		//回复文件类型消息
		if(WechatMsgConfig.Rettype_File.equals(kr.getRetType())) {
			MessageTools.sendFileMsgByUserId(fromUserName, kr.getFilePath());
		}
		
		// 回复图片消息
		if(WechatMsgConfig.Rettype_Pic.equals(kr.getRetType())) {
			MessageTools.sendPicMsgByUserId(fromUserName, kr.getFilePath());
		}
		return null;
	}

	@Override
	public String picMsgHandle(BaseMsg msg) {
		WechatQueueService.pushTask(new MsgsaveCallable(msg));
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());// 这里使用收到图片的时间作为文件名
		String picPath = WechatConsts.PicMsgStoragePath + File.separator + fileName + ".jpg"; // 调用此方法来保存图片
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.PIC.getType(), picPath); // 保存图片的路径
		return null;
	}

	@Override
	public String voiceMsgHandle(BaseMsg msg) {
		WechatQueueService.pushTask(new MsgsaveCallable(msg));
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String voicePath = WechatConsts.VoiceMsgStoragePath + File.separator + fileName + ".mp3";
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.VOICE.getType(), voicePath);
		return null;
	}

	@Override
	public String viedoMsgHandle(BaseMsg msg) {
		WechatQueueService.pushTask(new MsgsaveCallable(msg));
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String viedoPath = WechatConsts.VideoMsgStoragePath + File.separator + fileName + ".mp4";
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.VIEDO.getType(), viedoPath);
		return null;
	}

	// 收到名片消息
	@Override
	public String nameCardMsgHandle(BaseMsg msg) {
		WechatQueueService.pushTask(new MsgsaveCallable(msg));
		return null;
	}

	@Override
	public void sysMsgHandle(BaseMsg msg) { // 收到系统消息
		WechatQueueService.pushTask(new MsgsaveCallable(msg));
		String text = msg.getContent();
		log.info(text);
	}

	@Override
	public String verifyAddFriendMsgHandle(BaseMsg msg) {
		WechatQueueService.pushTask(new MsgsaveCallable(msg));
		MessageTools.addFriend(msg, true); // 同意好友请求，false为不接受好友请求
		RecommendInfo recommendInfo = msg.getRecommendInfo();
		String nickName = recommendInfo.getNickName();
		String province = recommendInfo.getProvince();
		String city = recommendInfo.getCity();
		String text = "你好，来自" + province + city + "的" + nickName + "， 欢迎添加我为好友！";
		log.info(text);
		return null;
	}

	@Override
	public String mediaMsgHandle(BaseMsg msg) {
		WechatQueueService.pushTask(new MsgsaveCallable(msg));
		String fileName = msg.getFileName();
		String filePath = WechatConsts.FileMsgStoragePath + File.separator + fileName; // 这里是需要保存收到的文件路径，文件可以是任何格式如PDF，WORD，EXCEL等。
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.MEDIA.getType(), filePath);
		//		return "文件" + fileName + "保存成功";
		return null;
	}
}
