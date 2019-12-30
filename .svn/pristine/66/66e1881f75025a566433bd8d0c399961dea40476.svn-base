package com.yjt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yjt.entity.po.Company;
import com.yjt.entity.vo.MessageJob;
import com.yjt.service.CompanyService;
import com.yjt.util.SpringContextUtil;

@RestController
@DependsOn("springContextUtil")
public class TestController {

	@Autowired
	CompanyService companyService;
	
	@RequestMapping("/async")
	public String async() throws InterruptedException{
		
		companyService.asyncTest();
		System.out.println("会等5s吗");
		return "成功";
	}
	
	@RequestMapping("/beans")
	public String beans() {
		
		Map<String, MessageJob> mapFacotry = SpringContextUtil.getApplicationContext().getBeansOfType(MessageJob.class, true, true);
		System.out.println(mapFacotry);
		for(String key : mapFacotry.keySet()){
			System.out.println(key);
		}
		return "成功";
	}
	//ߘ ߘ 江涛ߘ ߘ
	@RequestMapping("/esave")
	public String save() {
		
		Company company = new Company();
		company.setCompanyCode("12345678");
		company.setCompanyName("��都嗨��、齐静��给你��");
		company.setType(1);
		companyService.insert(company);
		System.out.println(JSON.toJSONString(company));
		return "成功";
	}
}
