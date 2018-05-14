package com.huhusky.microservice.eureka.event;

import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;

/**
 * 对应 eureka 中 EurekaInstanceRegisteredEvent
 * @author huhu
 *
 */
@Component
public class InstanceRegisteredEventListener implements ApplicationListener<EurekaInstanceRegisteredEvent>{

	@Override
	public void onApplicationEvent(EurekaInstanceRegisteredEvent event) {
		System.out.println("#### 通过 implements AppliationListener 来监听事件 服务注册 ####");
		InstanceInfo info = event.getInstanceInfo();
		int duration = event.getLeaseDuration();
		Object source = event.getSource();
		Long timestamp = event.getTimestamp();
		boolean isReplication = event.isReplication();

		System.out.println(String.format("InstanceInfo=%s, duration=%s, isReplication=%s, timestamp=%s, source=%s", 
				info, duration, isReplication, timestamp, source));
	}

}
