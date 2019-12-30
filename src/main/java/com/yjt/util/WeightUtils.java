package com.yjt.util;

import java.math.BigDecimal;

public class WeightUtils {

	public static final String WEIGHT = "\\-?[0-9]+";
	
	/**  
     * 将十分之一克的转换为克 （除10）  
     *   
     * @param weight  
     * @return  
     * @throws Exception   
     */   
    public static String changeF2G(Long weight) throws Exception{    
    	if(!weight.toString().matches(WEIGHT)) {    
            throw new Exception("重量格式有误！");    
        }    
        return BigDecimal.valueOf(weight).divide(new BigDecimal(10)).toString();  
    }
    
    /**  
     * 将克的转换千克 （除1000）  
     *   
     * @param weight  
     * @return  
     * @throws Exception   
     */   
    public static String changeG2KG(Long weight) throws Exception{    
    	if(weight == null){
    		return"";
    	}
    	if(!weight.toString().matches(WEIGHT)) {    
            throw new Exception("重量格式有误！");    
        }    
        return BigDecimal.valueOf(weight).divide(new BigDecimal(1000)).toString();  
    }
    
    /**  
     * 将十分之一克的转换为千克克 （除10000）  
     *   
     * @param weight  
     * @return  
     * @throws Exception   
     */   
    public static String changeF2KG(Long weight) throws Exception{    
    	if(!weight.toString().matches(WEIGHT)) {    
            throw new Exception("重量格式有误！");    
        }    
        return BigDecimal.valueOf(weight).divide(new BigDecimal(10000)).toString();  
    }
    
    /**
     * KG转G 乘1000
     *
     * @param amount
     * @return
     */
    public static long changeKG2K(String amount) throws Exception{

        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        long amLong = 0l;
        if(index == -1){
            amLong = Long.valueOf(currency+"000");
        }else if(length - index == 1){
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"000");
        }else if(length - index == 2){
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+"00");
        }else if(length - index == 3){
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", "")+"0");
        }else if(length - index == 5){
            amLong = Long.valueOf((currency.substring(0, index+5)).replace(".", ""));
        }else{
            throw new RuntimeException("金额格式有误！");
        }
        return amLong;
    }
}
