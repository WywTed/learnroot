package com.huhusky.microservice.eureka.event;

import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;

/**
 * 
针对 eureka 实例所有的变更状态监控，对应eureka中提供的5个事件
	EurekaInstanceCanceledEvent 服务下线事件
	EurekaInstanceRegisteredEvent 服务注册事件
	EurekaInstanceRenewedEvent 服务续约事件
	EurekaRegistryAvailableEvent Eureka注册中心启动事件
	EurekaServerStartedEvent Eureka Server启动事件
 * @author huhu
 *
 */
@Component
public class InstanceStateChangeEventListener {

	/**
	 * 服务下线
	 * @param event
	 */
	@EventListener
	public void listen(EurekaInstanceCanceledEvent event) {
		System.out.println("#### 服务下线 ####");
		String appName = event.getAppName();
		String serverId = event.getServerId();
		Object source = event.getSource();
		Long timestamp = event.getTimestamp();
		boolean isReplication = event.isReplication();

		System.out.println(String.format("appName=%s, serverId=%s, isReplication=%s, timestamp=%s, source=%s", 
				appName, serverId, isReplication, timestamp, source));
	}

	/**
	 * 服务注册
	 * @param event
	 */
	@EventListener
	public void listen(EurekaInstanceRegisteredEvent event) {
		System.out.println("#### 服务注册 ####");
		InstanceInfo info = event.getInstanceInfo();
		int duration = event.getLeaseDuration();
		Object source = event.getSource();
		Long timestamp = event.getTimestamp();
		boolean isReplication = event.isReplication();

		System.out.println(String.format("InstanceInfo=%s, duration=%s, isReplication=%s, timestamp=%s, source=%s", 
				info, duration, isReplication, timestamp, source));
	}

	/**
	 * 服务续约
	 * @param event
	 */
	@EventListener
	public void listen(EurekaInstanceRenewedEvent event) {
		System.out.println("#### 服务续约 ####");
		String appName = event.getAppName();
		String serverId = event.getServerId();
		Object source = event.getSource();
		Long timestamp = event.getTimestamp();
		boolean isReplication = event.isReplication();
		System.out.println(String.format("appName=%s, serverId=%s, isReplication=%s, timestamp=%s, source=%s", 
				appName, serverId, isReplication, timestamp, source));
	}

	/**
	 * 注册中心启动
	 * @param event
	 */
	@EventListener
	public void listen(EurekaRegistryAvailableEvent event) {
		System.out.println("#### 注册中心启动 ####");
		Object source = event.getSource();
		Long timestamp = event.getTimestamp();
		System.out.println(String.format("timestamp=%s, source=%s", timestamp, source));
	}

	/**
	 * eureka server 启动
	 * @param event
	 */
	@EventListener
	public void listen(EurekaServerStartedEvent event) {
		System.out.println("#### eureka server 启动 ####");
		Object source = event.getSource();
		Long timestamp = event.getTimestamp();
		System.out.println(String.format("timestamp=%s, source=%s", timestamp, source));
	}

}
