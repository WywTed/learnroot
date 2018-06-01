package com.huhusky.tfc.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.huhusky.tfc.wechat.msg.Keyword;
import com.huhusky.tfc.wechat.msg.KeywordRet;
import com.huhusky.tfc.wechat.msg.KeywordRetMap;

@Mapper
public interface MsgDao {

	
	@Select("select * from msg_keyword where status='normal'")
	List<Keyword> listKeywords();
	
	@Select("select * from msg_ret where status='normal'")
	List<KeywordRet> listKeywordRet();
	
	@Select("select * from msg_keyword_ret where status='normal'")
	List<KeywordRetMap> listKeywordMap();
	
}
