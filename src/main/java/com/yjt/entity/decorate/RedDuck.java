package com.yjt.entity.decorate;

public class RedDuck extends Duck{

	@Override
	public String say() {
		String word = "i am redDuck";
		System.out.println(word);
		return word;
	}

}
