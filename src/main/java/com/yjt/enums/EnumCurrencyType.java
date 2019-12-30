package com.yjt.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum EnumCurrencyType {

	CNY("人民币","CNY"),
	USD("美元","USD"),
	EUR("欧元","EUR");

	private String name;

	private String value;

	EnumCurrencyType(String name, String value)
	{
		this.name = name;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
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

	public static Map<String,String> toMap()
	{
		Map<String,String> map = new LinkedHashMap<String, String>();
		for(EnumCurrencyType e : EnumCurrencyType.values())
		{
			map.put(e.getValue(), e.getName());
		}
		return map;
	};

}
