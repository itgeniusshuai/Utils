package com.utils.rpc;

public class HelloServiceImpl implements HelloService{

	@Override
	public void hello() {
		System.out.println("hello world!!!");
	}

	@Override
	public int get(String i) {
		return i.length();
	}

}
