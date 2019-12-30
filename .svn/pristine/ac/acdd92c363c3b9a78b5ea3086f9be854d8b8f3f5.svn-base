package com.yjt.entity.proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String[] args){
		ProxyTest test = new ProxyTest();
		test.drive();
	}
	
	public void drive(){
		PersonBeanImpl jojo = new PersonBeanImpl();
		jojo.setName("jojo");
		PersonBean ownerProxy = getOwnerProxy(jojo);
		System.out.println("name is"+ownerProxy.getName());
		ownerProxy.setInterests("bowling go");
		System.out.println("Interests set from owner proxy");
		try{
			ownerProxy.setHotOrNotRating(10);
		}catch(Exception e){
			System.out.println("can't set rating from owner proxy");
		}
		
	}
	
	PersonBean getOwnerProxy(PersonBean person){
		
		Object object = Proxy.newProxyInstance(person.getClass().getClassLoader(),
				person.getClass().getInterfaces(), new OwnerInvocationHandler(person));
		return (PersonBean) object;
	}
}
