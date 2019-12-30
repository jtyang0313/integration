package com.yjt.enums;

import java.util.LinkedHashMap;
import java.util.Map;


public enum YesNoEnum {

	YES("是",1),NO("否",0);
	
	private String name;
	
	private int value;
	
	YesNoEnum(String name, int value)
	{
		this.name = name;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public static Map<Integer,String> getMap()
    {
    	Map<Integer,String> map = new LinkedHashMap<Integer, String>();
    	for(YesNoEnum e : YesNoEnum.values())
    	{
    		map.put(e.getValue(), e.getName());
    	}
    	return map;
    };
}
