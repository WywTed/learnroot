package com.huhusky.tfc.wechat.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecommendInfo {

	private String ticket;
	private String userName;
	private int sex;
	private int attrStatus;
	private String city;
	private String nickName;
	private int scene;
	private String province;
	private String content;
	private String alias;
	private String signature;
	private int opCode;
	private int qQNum;
	private int verifyFlag;
}
