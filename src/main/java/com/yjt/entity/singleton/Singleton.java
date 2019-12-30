package com.yjt.entity.singleton;

public class Singleton {

	private static volatile Singleton uniqueInstance;
	
	private Singleton(){}
	
	public static Singleton getInstance(){
		
		if(uniqueInstance == null){
			synchronized (Singleton.class) {
				// 这一次判断也是必须的，不然会有并发问题
				if(uniqueInstance == null){
					uniqueInstance = new Singleton();
				}
			}
		}
		return uniqueInstance;
	}
}
