package com.huhusky.tfc.wechat.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huhusky.tfc.wechat.dto.BaseMsg;
import com.huhusky.tfc.wechat.msg.KeywordRet;
import com.huhusky.tfc.wechat.msg.MsgConfig;
import com.huhusky.tfc.wechat.service.MsgService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/tfmsg")
@Slf4j
public class TfmsgController {
	
	@Autowired
	private MsgService msgService;
	

	@ApiOperation("获取微信消息回复的配置，如消息类型为text时回复什么类型")
	@GetMapping("/config")
	private MsgConfig msgConfig() {
		return msgService.buildMsgConfig();
	}
	
	@GetMapping("/price")
	public String retTfcPrice() {
		return msgService.getTfprice();
	}
	
	@RequestMapping("/test/{threadCount}/{taskCount}")
	public void test(@PathVariable int threadCount, @PathVariable int taskCount) {
		ExecutorService es = Executors.newFixedThreadPool(threadCount);
		for(int i=0;i<taskCount;i++) {
			es.submit(new Runnable() {
				@Override
				public void run() {
					long start = System.currentTimeMillis();
					msgService.getTfprice();
					long end = System.currentTimeMillis();
					log.info(String.format("###### %s 耗时： %s", Thread.currentThread().getName(), end - start));
				}
			});
		}
	}
	
	@PostMapping("/onreceive/{msgType}")
	public KeywordRet onReceiveMsg(@PathVariable String msgType, @RequestBody BaseMsg msg) {
		
		// TODO if necessary
		
		KeywordRet kr = new KeywordRet();
		kr.setContent("## 我已收到你的消息  ##");
		kr.setRetType("text");
		
		return kr;
	}
	
}
