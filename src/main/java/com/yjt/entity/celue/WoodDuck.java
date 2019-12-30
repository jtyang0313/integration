package com.yjt.entity.celue;

public class WoodDuck extends Duck{

	public WoodDuck(){
		flyBehavior = new FlyNoWay();
	}
	
	@Override
	void display() {
		System.out.println("i'm a wood duck!");
	}

	@Override
	void flyPerform() {
		flyBehavior.fly();
	}

}
