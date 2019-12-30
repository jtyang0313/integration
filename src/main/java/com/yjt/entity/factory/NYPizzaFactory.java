package com.yjt.entity.factory;

//PizzaFactory
public class NYPizzaFactory extends PizzaFactory {

	@Override
	Pizza createPizza(String type) {
//		if(pizzaMap.containsKey(type)){
//			return pizzaMap.get(type);
//		}else{
//			return null;
//		}
		if(type.equals("cheese")){
			return new NYCheesePizza();
		}else if(type.equals("clam")){
			return new NYClamPizza();
		}else{
			return null;
		}
	}

	
}
