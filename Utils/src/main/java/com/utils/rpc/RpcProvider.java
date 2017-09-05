package com.utils.rpc;

public class RpcProvider {
	public static void main(String[] args) {
		HelloService helloService = new HelloServiceImpl();
		try {
			RpcUtils.export(helloService, HelloService.class, 12345);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
