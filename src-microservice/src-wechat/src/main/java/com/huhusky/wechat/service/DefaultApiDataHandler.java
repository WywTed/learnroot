package com.huhusky.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huhusky.wechat.dao.ApiDataDao;

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

}
