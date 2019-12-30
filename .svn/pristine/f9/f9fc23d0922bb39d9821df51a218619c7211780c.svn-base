package com.yjt.config.interceptor;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;
import com.yjt.entity.vo.ResponseResult;

/**
 * @Author: wangzsky
 * @Date: 2019/9/26 14:35
 */
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String auth = request.getHeader("auth");
        log.info("请求进入 auth={}", auth);
        log.info("已经进入拦截器");
        if(auth!=null && auth.length()>0){
        	return true;
        }
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        response.getWriter().write(JSON.toJSONString(ResponseResult.fail(0, "请先登录")));
        response.getWriter().flush();
        response.getWriter().close();
        log.info("请求进入 失败");

        return false;


    }
}
