package com.huhusky.wechat.cons;

import java.io.File;

public class WechatConsts {

	public static String ItchatDataBasePath = "c:/itchat/data";
	public static String QrcodeStoragePath = ItchatDataBasePath + "/loginqrcode";
	public static String AvatarStoragePath = ItchatDataBasePath + "/avatar";
	
	public static String MsgStoragePath = ItchatDataBasePath + "/msg";
	public static String PicMsgStoragePath = MsgStoragePath + "/pic";
	public static String VideoMsgStoragePath = MsgStoragePath + "/video";
	public static String VoiceMsgStoragePath = MsgStoragePath + "/voice";
	public static String FileMsgStoragePath = MsgStoragePath + "/file";
	
	
	static {
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
