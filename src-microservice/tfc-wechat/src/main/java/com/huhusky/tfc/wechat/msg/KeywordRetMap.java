package com.huhusky.tfc.wechat.msg;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
public class KeywordRetMap {
	private Long keywordId;
	private Long keywordRetId;
	private String status;
	/*public Long getKeywordId() {
		return keywordId;
	}
	public void setKeywordId(Long keywordId) {
		this.keywordId = keywordId;
	}
	public Long getKeywordRetId() {
		return keywordRetId;
	}
	public void setKeywordRetId(Long keywordRetId) {
		this.keywordRetId = keywordRetId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}*/
}
