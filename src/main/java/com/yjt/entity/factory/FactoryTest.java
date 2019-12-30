package com.yjt.entity.factory;

import java.util.Map;

public class FactoryTest {

	public static void main(String[] args){
		PizzaFactory nyPizzaStore = new NYPizzaFactory();
		Pizza nypizza = nyPizzaStore.createPizza("cheese");
		nypizza.print();
	}
}
