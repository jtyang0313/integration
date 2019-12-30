package com.yjt.entity.celue;

public class WhiteDuck extends Duck{

	public WhiteDuck(){
		flyBehavior = new FlyWithWing();
	}
	
	@Override
	void display() {
		System.out.println("my feather color is white!");
	}

	@Override
	void flyPerform() {
		flyBehavior.fly();
	}

	
}
