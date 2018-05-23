package com.huhusky.wechat.service;

import java.util.concurrent.Callable;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.alibaba.fastjson.JSON;
import com.huhusky.common.utils.util.SpringBootUtil;
import com.huhusky.wechat.dao.ApiDataDao;

import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class MsgsaveCallable implements Callable<Void>{
	
	private BaseMsg msg;
	
	public MsgsaveCallable(BaseMsg msg) {
		this.msg = msg;
	}

	@Override
	public Void call() throws Exception {
		try {
			ApiDataDao apiDataDao = SpringBootUtil.getBean(ApiDataDao.class);
			if(msg.getRecommendInfo() != null) {
				msg.setData(JSON.toJSONString(msg.getRecommendInfo()));
			}
			if(msg.getAppInfo() != null) {
				
				msg.setAppInfoJson(JSON.toJSONString(msg.getAppInfo()));
			}
			apiDataDao.addMsg(msg);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

}
