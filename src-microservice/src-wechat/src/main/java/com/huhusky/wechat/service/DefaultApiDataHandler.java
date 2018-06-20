package com.huhusky.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huhusky.wechat.dao.ApiDataDao;
import com.huhusky.wechat.pojo.GroupInfo;

import cn.zhouyafeng.itchat4j.api.WxapiDataHandler;
import cn.zhouyafeng.itchat4j.api.dto.Contact;

@Service
public class DefaultApiDataHandler implements WxapiDataHandler{
	
	@Autowired
	private ApiDataDao apiDataDao;

	@Override
	public void onGetContact(List<Contact> contacts) {
		if(contacts != null && !contacts.isEmpty()) {
			for(Contact contact : contacts) {
				Contact db = apiDataDao.getContactByUserName(contact.getUserName());
				if(db == null) {
					apiDataDao.addContact(contact);
				}else {
					// TODO update
				}
			}
		}
	}

	@Override
	public void onBatchGetContact(String text) {
		GroupInfo gi = new GroupInfo();
		gi.setCreateTime(System.currentTimeMillis());
		gi.setInfo(text);
		apiDataDao.addGroupInfo(gi);
	}

}
