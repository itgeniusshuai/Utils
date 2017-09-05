package com.utils.rpc;

public class RpcConsumer {
	public static void main(String[] args) {
		try {
			HelloService helloService = RpcUtils.refer(HelloService.class, "localhost", 12345);
			helloService.hello();
			int i = helloService.get("dfd");
			System.out.println(i);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
