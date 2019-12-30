package com.yjt.entity.factory;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.yjt.util.SpringContextUtil;

/**
 * 工厂方法模式
 * 改进的工厂模式
 * @author yjt
 * @date 2019年12月12日
 */
//@Component
//@DependsOn("springContextUtil")
public abstract class PizzaFactory {

//	static Map<String, Pizza> pizzaMap = new HashMap<String, Pizza>();
//	
//	@PostConstruct
//	public void init(){
//		Map<String, Pizza> pizzas = SpringContextUtil.getApplicationContext().getBeansOfType(Pizza.class, true, true);
//		for(Map.Entry<String, Pizza> m : pizzas.entrySet()){
//			pizzaMap.put(m.getKey(), m.getValue());
//		}
//	}
	
	abstract Pizza createPizza(String type);
}
