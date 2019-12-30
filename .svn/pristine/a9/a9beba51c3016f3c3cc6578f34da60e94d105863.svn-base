package com.yjt.entity.vo;

import lombok.Getter;

import java.io.Serializable;

/**
 * restful api 返回类封装
 * @author qjk 2019/3/25
 * @param <T> 泛型类
 */
@Getter
public class ResponseResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int RESULT_CODE_OK=200;//返回成功编码
    private static final int RESULT_CODE_FAIL=-1;//错误返回编码
    private static final String RESULT_MESSAGE_OK="执行成功";
    private static final String RESULT_MESSAGE_FAIL="执行失败";

    private int code;
    private String message;
    private T data;

    private ResponseResult() {
        this.code=RESULT_CODE_OK;
        this.message=RESULT_MESSAGE_OK;
        this.data=null;
    }

    private ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回正确方法，使用默认值
     * code:0
     * message:成功
     * data: null
     * @return
     */
    public static<T> ResponseResult<T> ok(){
        return new ResponseResult();
    }

    public static<T> ResponseResult<T> ok(int code){
        return new ResponseResult(code,RESULT_MESSAGE_OK,null);
    }

    public static<T> ResponseResult<T> ok(String message){
        return new ResponseResult(RESULT_CODE_OK,message,null);
    }

    public static<T> ResponseResult<T> ok(T data){
        return new ResponseResult(RESULT_CODE_OK,RESULT_MESSAGE_OK,data);
    }

    public static<T> ResponseResult<T> ok(int code,String message){
        return new ResponseResult(code,message,null);
    }

    public static<T> ResponseResult<T> ok(String message,T data){
        return new ResponseResult(RESULT_CODE_OK,message,data);
    }

    public static<T> ResponseResult<T> ok(int code,String message,T data){
        return new ResponseResult(code,message,data);
    }
    /**----------------------------------------------------------------------------------------------------**/

    /**
     * 错误返回方法，使用默认值
     * code:-1
     * message: 执行失败
     * data: null
     * @return
     */
    public static<T> ResponseResult<T> fail(){
        return new ResponseResult(RESULT_CODE_FAIL,RESULT_MESSAGE_FAIL,null);
    }

    public static<T> ResponseResult<T> fail(int code){
        return new ResponseResult(code,RESULT_MESSAGE_FAIL,null);
    }

    public static<T> ResponseResult<T> fail(String message){
        return new ResponseResult(RESULT_CODE_FAIL,message,null);
    }

    public static<T> ResponseResult<T> fail(T data){
        return new ResponseResult(RESULT_CODE_FAIL,RESULT_MESSAGE_FAIL,data);
    }

    public static<T> ResponseResult<T> fail(int code,String message){
        return new ResponseResult(code,message,null);
    }

    public static<T> ResponseResult<T> fail(String message,T data){
        return new ResponseResult(RESULT_CODE_FAIL,message,data);
    }

    public static<T> ResponseResult<T> fail(int code,String message,T data){
        return new ResponseResult(code,message,data);
    }

}
