package com.huhusky.wechat.msg;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SendMsgReqbody {

	String toUserName;
	String msgType;
	String filePath;
	String content;
}
