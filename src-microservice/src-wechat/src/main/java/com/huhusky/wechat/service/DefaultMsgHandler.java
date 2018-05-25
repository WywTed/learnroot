package com.huhusky.wechat.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.ws.Dispatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huhusky.wechat.cons.KeywordArr;
import com.huhusky.wechat.cons.WechatConsts;

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
	private CoinService coinService;
	
	
	@Override
	public String textMsgHandle(BaseMsg msg) {
		WechatQueueService.pushTask(new MsgsaveCallable(msg));
		return resolveTextMsg(msg);
		
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
	}

	private String resolveTextMsg(BaseMsg msg) {
		String text = msg.getText();
		if(KeywordArr.Key_PriceArr.contains(text.toUpperCase())) {
			return coinService.getTfprice();
		}
		if(KeywordArr.Key_FeedbakArr.contains(text)) {
			return "------------------------\n"
					+ "TFchain全球第一出行公链\n"
					+ "开车和出行可以挖矿，挖矿可以赚钱\n"
					+ "------------------------\n"
					+ "TF社群反馈和意见:\n"
					+ "https://t.zsxq.com/BImEuN3\n"
					+ "TF官方微信平台:\n"
					+ "TFCOIN\n"
					+ "电报群入口及使用教程:\n"
					+ "https://mp.weixin.qq.com/s/YK3f764wUt1Jbu9o8Rc2ug\n"
					+ "TF知识星球官方社群:\n"
					+ "https://t.zsxq.com/BImEuN3\n"
					+ "TF大讲堂:\n"
					+ "https://appomy79xgp7344.h5.xiaoeknow.com/\n"
					+ "------------------------\n";
		}
		if(KeywordArr.Key_WorthArr.contains(text)) {
			return "------------------------\n"
					+ "使命：加速全球出行效率\n"
					+ "愿景：TFchain 成为全球出行第一公链\n"
					+ "价值观：公平 民主 责任心 主动 激情 互助\n"
					+ "------------------------\n";
		}
		if(KeywordArr.Key_Tutor.equals(text)) {
			return "------------------------\n"
					+ "TFchain全球第一出行公链\n"
					+ "开车和出行可以挖矿，挖矿可以赚钱\n"
					+ "------------------------\n"
					+ "挖矿APP教程\n"
					+ "https://mp.weixin.qq.com/s/Kh0BXNRFgHwS0dnOiDBEew\n"
					+ "手机钱包教程:\n"
					+ "https://mp.weixin.qq.com/s/rLjBqWfDYyZBHAW1Kg8F6Q\n"
					+ "PC钱包导入手机轻钱包教程:\n"
					+ "https://mp.weixin.qq.com/s/eDVlxfCcpw02iOcafLx4mw\n"
					+ "提币教程:\n"
					+ "https://mp.weixin.qq.com/s/Z1lk7lV9WNsCEonF61Lpvg\n"
					+ "------------------------\n";
		}
		if(KeywordArr.Key_Download.equals(text)) {
			return "------------------------\n"
					+ "TFchain全球第一出行公链\n"
					+ "开车和出行可以挖矿，挖矿可以赚钱\n"
					+ "------------------------\n"
					+ "挖矿APP\n"
					+ "iOS（请卸载旧的版本，然后安装新的）：\n"
					+ "https://fir.im/TFChain\n"
					+ "Android（请卸载旧的版本，然后安装新的）：\n"
					+ "http://d.tfcoin.io/TFChain.apk\n"
					+ "手机钱包:\n"
					+ "IOS:\n"
					+ "https://fir.im/TFWallet\n"
					+ "安卓：\n"
					+ "http://d.tfcoin.io/TFWallet.apk\n"
					+ "------------------------\n";
		}
		
		return null;
	}

	@Override
	public String picMsgHandle(BaseMsg msg) {
		WechatQueueService.pushTask(new MsgsaveCallable(msg));
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());// 这里使用收到图片的时间作为文件名
		String picPath = WechatConsts.PicMsgStoragePath + File.separator + fileName + ".jpg"; // 调用此方法来保存图片
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.PIC.getType(), picPath); // 保存图片的路径
//		return "图片保存成功";
		return null;
	}

	@Override
	public String voiceMsgHandle(BaseMsg msg) {
		WechatQueueService.pushTask(new MsgsaveCallable(msg));
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String voicePath = WechatConsts.VoiceMsgStoragePath + File.separator + fileName + ".mp3";
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.VOICE.getType(), voicePath);
//		return "声音保存成功";
		return null;
	}

	@Override
	public String viedoMsgHandle(BaseMsg msg) {
		WechatQueueService.pushTask(new MsgsaveCallable(msg));
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String viedoPath = WechatConsts.VideoMsgStoragePath + File.separator + fileName + ".mp4";
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.VIEDO.getType(), viedoPath);
//		return "视频保存成功";
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
//		return text;
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
