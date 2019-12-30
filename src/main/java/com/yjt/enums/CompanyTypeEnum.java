package com.yjt.enums;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public enum CompanyTypeEnum {

    PFU(1,"pfu"),
    DKP(2,"dkp"),
    GKP(3,"gkp"),
    PVP(4,"pvp"),
    SF(5,"咸鱼");

    private Integer value;
    private String name;

    CompanyTypeEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public static Map<Integer,String> getMap()
    {
        Map<Integer,String> map = new LinkedHashMap<Integer, String>();
        for(CompanyTypeEnum e : CompanyTypeEnum.values())
        {
            map.put(e.getValue(), e.getName());
        }
        return map;
    }


}
