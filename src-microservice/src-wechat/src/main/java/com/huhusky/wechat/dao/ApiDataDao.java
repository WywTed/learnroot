package com.huhusky.wechat.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.huhusky.wechat.pojo.GroupInfo;

import cn.zhouyafeng.itchat4j.api.dto.Contact;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;

@Mapper
public interface ApiDataDao {
	
	int addContact(Contact contact);
	
	int addMsg(BaseMsg msg);
	
	@Select("select * from contact where UserName=#{userName}")
	Contact getContactByUserName(@Param("userName")String userName);

	int addGroupInfo(GroupInfo gi);

}
