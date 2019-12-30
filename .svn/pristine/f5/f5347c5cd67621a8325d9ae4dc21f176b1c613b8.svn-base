package com.yjt.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;


/**
 *
 * 元 角 分 厘 毫 丝
 * 
 * @author liuwd
 *
 */
public class MoneyUtils {

    /**
     * 将毫转换为元并返回金额格式的字符串 （除10000）
     *   
     * @param amount  
     * @return  
     * @throws Exception   
     */    
    public static String changeH2Y(Long amount) throws Exception{
    	if(amount==null) {
    		return "";
    	}
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(10000)).toString();
    }

    /**
     * 将毫转换为元并返回金额格式的字符串 （除10000）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeH2Y(String amount) throws Exception{
    	if(StringUtils.isNotEmpty(amount)) {
    		 return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(10000)).toString();
    	}
    	return "";
    }

    /**
     * 将元为单位的转换为毫 （乘10000）
     *
     * @param amount
     * @return
     */
    public static String changeY2H(Long amount){
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(10000)).toString();
    }

    /**
     * 将元为单位的转换为毫 替换小数点，支持以逗号区分的金额
     *
     * @param amount
     * @return
     */
    public static Long changeY2H(String amount) throws Exception{
    	if (StringUtils.isEmpty(amount)) {
    		return null;
    	}
        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        long amLong = 0l;
        if(index == -1){
            amLong = Long.valueOf(currency+"0000");
        }else if(length - index == 1){
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"0000");
        }else if(length - index == 2){
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+"000");
        }else if(length - index == 3){
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", "")+"00");
        }else if(length - index == 4){
            amLong = Long.valueOf((currency.substring(0, index+4)).replace(".", "")+"0");
        }else if(length - index == 5){
            amLong = Long.valueOf((currency.substring(0, index+5)).replace(".", ""));
        }else{
            throw new RuntimeException("金额格式有误！");
        }
        return amLong;
    }

}