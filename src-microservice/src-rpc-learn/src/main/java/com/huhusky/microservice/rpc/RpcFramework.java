package com.huhusky.microservice.rpc;

import java.net.ServerSocket;

public class RpcFramework {

	public static void export(final Object service, int port) throws Exception {
		if (service == null) {
			throw new IllegalArgumentException("service instance == null");	
		}
		if(port <=0 || port > 65535) {
			throw new IllegalArgumentException("Invalid port: " + port);
		}
		System.out.println(String.format("Export service: %s on port %d", service.getClass().getName(), port));
		
		ServerSocket server = new ServerSocket(port);
		
		for(;;) {
			try {
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
