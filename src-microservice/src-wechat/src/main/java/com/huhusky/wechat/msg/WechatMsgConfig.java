package com.huhusky.wechat.msg;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class WechatMsgConfig {

	public static Msgconfig TextmsgConfig = new Msgconfig();
	
	public static KeywordRet getKeywordRet(String keyword) {
		if(!TextmsgConfig.getKeywords().contains(keyword)) {
			return null;
		}
		Long retKey = TextmsgConfig.getKeywordRetMapping().get(keyword);
		KeywordRet kr = TextmsgConfig.getKeywordRet().get(retKey);
		if(kr == null) {
			return null;
		}
		return kr;
	}
	
	public static boolean containKeyword(String keyword) {
		return TextmsgConfig.getKeywords().contains(keyword);
	}
	
	public static String Rettype_Text = "text";
	public static String Rettype_Pic = "picture";
	public static String Rettype_File = "file";
	
	@Getter
	@Setter
	@ToString
	public static class Msgconfig{
		private List<String> keywords;
		private Map<String, Long> keywordRetMapping;
		private Map<Long, KeywordRet> keywordRet;
	}
	
	@Getter
	@Setter
	@ToString
	public static class KeywordRet{
		private String retType; // 回复类型 文本， 图片， 文件，
		private boolean remote; // 是否远程获取结果
		private String url; // 当 remote=true 时， url 为获取结果的地址
		private String content; // 回复类型为文本时， 使用content
		private String filePath; // 如果回复的是图片，文件，则使用filepath
	}
}
