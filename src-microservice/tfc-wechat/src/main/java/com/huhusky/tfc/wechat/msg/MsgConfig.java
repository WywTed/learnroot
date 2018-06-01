package com.huhusky.tfc.wechat.msg;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MsgConfig {

	private List<String> keywords;
	private Map<String, Long> keywordRetMapping;
	private Map<Long, KeywordRet> keywordRet;

}
