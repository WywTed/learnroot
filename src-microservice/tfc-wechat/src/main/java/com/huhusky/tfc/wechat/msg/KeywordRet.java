package com.huhusky.tfc.wechat.msg;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
public class KeywordRet {
	private Long keywordRetId;
	private String retType; // 回复类型 文本， 图片， 文件，
	private boolean remote; // 是否远程获取结果
	private String url; // 当 remote=true 时， url 为获取结果的地址
	private String content; // 回复类型为文本时， 使用content
	private String filePath; // 如果回复的是图片，文件，则使用filepath
	private String status;
}
	