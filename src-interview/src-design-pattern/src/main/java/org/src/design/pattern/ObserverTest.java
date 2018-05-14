package org.src.design.pattern;

import java.util.ArrayList;
import java.util.List;

public class ObserverTest {

	public static void main(String[] args) {
		WechatServer ws = new WechatServer();
		WechatUser wu1 = new WechatUser("test1");
		WechatUser wu2 = new WechatUser("test2");
		WechatUser wu3 = new WechatUser("test3");
		ws.registObserver(wu1);
		ws.registObserver(wu2);
		ws.registObserver(wu3);
		ws.setMessage("------------开始测试");
		ws.removeObserver(wu2);
		ws.setMessage("------------继续测试");
		
	}
}

class WechatServer implements Observerable{
	
	private List<Observer> obs;
	private String message;

	
	public WechatServer() {
		obs = new ArrayList<>();
		message = "";
	}
	
	@Override
	public void registObserver(Observer observer) {
		System.out.println(String.format("## %s 注册为观察者", printObserver(observer)));
		obs.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		System.out.println(String.format("## %s 取消注册观察者", printObserver(observer)));
		obs.remove(observer);
	}

	@Override
	public void notifyObserver() {
		if(!obs.isEmpty()) {
			for(Observer ob : obs) {
				ob.update(this.message);
			}
		}
	}
	
	public void setMessage(String message) {
		this.message = message;
		System.out.println("## 微信服务更新消息");
		notifyObserver();
	}
	
	public String printObserver(Observer ob) {
		return ob.toString() + "-name-" + ob.getName();
	}
	
}

class WechatUser implements Observer{
	
	private String name;
	
	public WechatUser(String name) {
		this.name = name;
	}

	@Override
	public void update(String message) {
		System.out.println(String.format("## %s 接收消息： %s ", name, message));
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	
}