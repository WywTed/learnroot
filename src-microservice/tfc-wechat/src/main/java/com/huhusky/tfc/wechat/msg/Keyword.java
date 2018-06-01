package com.huhusky.tfc.wechat.msg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
public class Keyword {
	private Long keywordId;
	private String keyword;
	private String status;
}
