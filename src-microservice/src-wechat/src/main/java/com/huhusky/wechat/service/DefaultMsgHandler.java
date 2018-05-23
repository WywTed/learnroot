package com.huhusky.wechat.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huhusky.wechat.cons.KeywordArr;
import com.huhusky.wechat.cons.WechatConsts;

import cn.zhouyafeng.itchat4j.api.Core;
import cn.zhouyafeng.itchat4j.api.MessageTools;
import cn.zhouyafeng.itchat4j.api.WechatTools;
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
	private CoinService coinService;
	
	
	@Override
	public String textMsgHandle(BaseMsg msg) {
		String msgContent = msg.getText();
		if(KeywordArr.CoinArr.contains(msgContent.toUpperCase())) {
//			MessageTools.sendMsg(coinService.getTfprice(), msg.getFromUserName());
			return coinService.getTfprice();
		}
		
		/*log.info("==============msg: " + msgContent);
		// String docFilePath = "D:/itchat4j/pic/1.jpg"; // 这里是需要发送的文件的路径
		if (!msg.isGroupMsg()) { // 群消息不处理
			// String userId = msg.getString("FromUserName");
			// MessageTools.sendFileMsgByUserId(userId, docFilePath); // 发送文件
			// MessageTools.sendPicMsgByUserId(userId, docFilePath);
			String text = msg.getText(); // 发送文本消息，也可调用MessageTools.sendFileMsgByUserId(userId,text);
			log.info(text);
			if (text.equals("111")) {
				WechatTools.logout();
			}
			if (text.equals("222")) {
				WechatTools.remarkNameByNickName("yaphone", "Hello");
			}
			if (text.equals("333")) { // 测试群列表
				System.out.print(WechatTools.getGroupNickNameList());
				System.out.print(WechatTools.getGroupIdList());
				System.out.print(Core.getInstance().getGroupMemeberMap());
			}
			return text;
		}*/
		return null;
	}

	@Override
	public String picMsgHandle(BaseMsg msg) {
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());// 这里使用收到图片的时间作为文件名
		String picPath = WechatConsts.PicMsgStoragePath + File.separator + fileName + ".jpg"; // 调用此方法来保存图片
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.PIC.getType(), picPath); // 保存图片的路径
//		return "图片保存成功";
		return null;
	}

	@Override
	public String voiceMsgHandle(BaseMsg msg) {
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String voicePath = WechatConsts.VoiceMsgStoragePath + File.separator + fileName + ".mp3";
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.VOICE.getType(), voicePath);
//		return "声音保存成功";
		return null;
	}

	@Override
	public String viedoMsgHandle(BaseMsg msg) {
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String viedoPath = WechatConsts.VideoMsgStoragePath + File.separator + fileName + ".mp4";
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.VIEDO.getType(), viedoPath);
//		return "视频保存成功";
		return null;
	}

	// 收到名片消息
	@Override
	public String nameCardMsgHandle(BaseMsg msg) {
		return null;
	}

	@Override
	public void sysMsgHandle(BaseMsg msg) { // 收到系统消息
		String text = msg.getContent();
		log.info(text);
	}

	@Override
	public String verifyAddFriendMsgHandle(BaseMsg msg) {
		MessageTools.addFriend(msg, true); // 同意好友请求，false为不接受好友请求
		RecommendInfo recommendInfo = msg.getRecommendInfo();
		String nickName = recommendInfo.getNickName();
		String province = recommendInfo.getProvince();
		String city = recommendInfo.getCity();
		String text = "你好，来自" + province + city + "的" + nickName + "， 欢迎添加我为好友！";
//		return text;
		return null;
	}

	@Override
	public String mediaMsgHandle(BaseMsg msg) {
		String fileName = msg.getFileName();
		String filePath = WechatConsts.FileMsgStoragePath + File.separator + fileName; // 这里是需要保存收到的文件路径，文件可以是任何格式如PDF，WORD，EXCEL等。
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.MEDIA.getType(), filePath);
//		return "文件" + fileName + "保存成功";
		return null;
	}
}
