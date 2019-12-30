package com.yjt.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 对账审核状态枚举
 * @author yjt
 * @date 2019年7月16日
 */
public enum BillStateEnum
{
	CHECK_WAIT("待核对", 0),
	CHECK_PASS("已核对", 1);

	private String name;

	private int value;
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	BillStateEnum(String name, int value)
	{
		this.name = name;
		this.value = value;
	}
	
	public static Map<Integer, String> getMap()
    {
    	Map<Integer, String> map = new LinkedHashMap<Integer, String>();
    	for(BillStateEnum e : BillStateEnum.values())
    	{
    		map.put(e.getValue(), e.getName());
    	}
    	return map;
    };
}
