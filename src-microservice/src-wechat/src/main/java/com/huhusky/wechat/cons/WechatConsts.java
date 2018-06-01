package com.huhusky.wechat.cons;

import java.io.File;

import cn.zhouyafeng.itchat4j.utils.Config;

public class WechatConsts {
	
	public static String remoteConfigUrl = "http://localhost:5556/tfmsg/config";
	public static String remoteResolveMsgUrl = "http://localhost:5556/tfmsg/onreceive/%s";
	
	public static String LinuxBasePath = "/home/projectdata/itchat/data";
	public static String WindowBasePath = "c:/itchat/data";
	
	public static String ItchatDataBasePath = "";
	public static String QrcodeStoragePath = "";
	public static String AvatarStoragePath = "";
	
	public static String MsgStoragePath = "";
	public static String PicMsgStoragePath = "";
	public static String VideoMsgStoragePath = "";
	public static String VoiceMsgStoragePath = "";
	public static String FileMsgStoragePath = "";
	
	
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
		QrcodeStoragePath = ItchatDataBasePath + "/loginqrcode";
		AvatarStoragePath = ItchatDataBasePath + "/avatar";     
		                                                        
		MsgStoragePath = ItchatDataBasePath + "/msg";           
		PicMsgStoragePath = MsgStoragePath + "/pic";            
		VideoMsgStoragePath = MsgStoragePath + "/video";        
		VoiceMsgStoragePath = MsgStoragePath + "/voice";        
		FileMsgStoragePath = MsgStoragePath + "/file";          
		
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
