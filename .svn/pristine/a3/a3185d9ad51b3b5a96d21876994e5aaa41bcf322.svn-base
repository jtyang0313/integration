package com.yjt.entity.decorate;

public class FlyDuck extends Behavior{
	
	Duck duck;
	
	public FlyDuck(Duck duck){
		this.duck = duck;
	}
	
	void fly(){
		
	}

	@Override
	public String say() {
		String word = duck.say()+"i can fly";
		System.out.println(word);
		return word;
	}
}
