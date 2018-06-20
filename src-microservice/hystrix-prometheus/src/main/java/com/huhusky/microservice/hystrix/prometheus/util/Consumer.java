package com.huhusky.microservice.hystrix.prometheus.util;

public interface Consumer<T> {
	
	void accept(T t);

}
