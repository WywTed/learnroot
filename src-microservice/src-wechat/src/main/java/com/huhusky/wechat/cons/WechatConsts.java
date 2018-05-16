package com.huhusky.wechat.cons;

import java.io.File;

import cn.zhouyafeng.itchat4j.utils.Config;

public class WechatConsts {

	public static String LinuxBasePath = "/home/projectdata/itchat/data";
	public static String WindowBasePath = "c:/itchat/data";
	
	public static String ItchatDataBasePath = "";
	public static String QrcodeStoragePath = ItchatDataBasePath + "/loginqrcode";
	public static String AvatarStoragePath = ItchatDataBasePath + "/avatar";
	
	public static String MsgStoragePath = ItchatDataBasePath + "/msg";
	public static String PicMsgStoragePath = MsgStoragePath + "/pic";
	public static String VideoMsgStoragePath = MsgStoragePath + "/video";
	public static String VoiceMsgStoragePath = MsgStoragePath + "/voice";
	public static String FileMsgStoragePath = MsgStoragePath + "/file";
	
	
	static {
		switch (Config.getOsNameEnum()) {
		case WINDOWS:
			ItchatDataBasePath = WindowBasePath;
			break;
		case LINUX:
			ItchatDataBasePath = LinuxBasePath;
			break;
		default:
			ItchatDataBasePath = LinuxBasePath;
			break;
		}
		mkdirs(QrcodeStoragePath, AvatarStoragePath, PicMsgStoragePath, VideoMsgStoragePath, VoiceMsgStoragePath, FileMsgStoragePath);
	}
	
	public static void mkdirs(String... paths) {
		if(paths != null && paths.length > 0){
			File directory;
			for(String path : paths) {
				directory = new File(path);
				if(!directory.exists()) {
					directory.mkdirs();
				}
			}
		}
	}
	
}
