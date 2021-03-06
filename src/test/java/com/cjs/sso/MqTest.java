package com.cjs.sso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjt.config.mq.CompanySender;
import com.yjt.entity.po.Company;

@RunWith(SpringRunner.class)
public class MqTest {

	@Autowired
	CompanySender companySender;
	
	@Test
	public void mqtest() {
		
		Company company = new Company();
		company.setCompanyCode("123");
		company.setCompanyName("yjt");
		company.setTelPhone("18766");
		companySender.sendMsg(company);
	}
	
}
