package com.yjt.entity.factory;

public enum PizzaEnum {

	cheese("cheese", "i am cheesePizza"),
	clam("clam", "i am clamPizza"),
	apple("apple", "i am applePizza");
	
	private String name;
	private String memo;
	
	PizzaEnum(String name, String memo){
		this.name = name;
		this.memo = memo;
	}
	
	public String getName() {
		return name;
	}
	public String getMemo() {
		return memo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
