package com.huhusky.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import feign.Param;

@Mapper
public interface CoinDao {

//	@Insert("insert into keyword_map()")
//	int insertKeywordMap(String[] words, int id);
	@Select("select a.text from kw_ret a join kw_ret_map b on a.textId=b.kwTextId where b.keyword=#{keyword}")
	String getRetByKeyword(@Param("keyword")String keyword);

	@Select("select distinct(keyword) from kw_ret_map")
	List<String> getAllKeywords();

	@Select("select keyword from kw_ret_url")
	List<String> getRemoteKeywords();
	
	@Select("select url from keyword from kw_ret_url where keyword = #{keyword}")
	List<String> getKeywordUrlByKw(@Param("keyword")String keyword);
	
	@Select("select configValue from coin_config where configkey='List' and configgroup='TfcoinKeywordShow'")
	String getKeywordShow();
	
}
