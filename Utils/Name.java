package com.xin.base.controller.user;

public class Name {
	private String name;
	private Integer sex;
	
	
	public Name(String name, Integer sex) {
		super();
		this.name = name;
		this.sex = sex;
	}
	public Name() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
}
