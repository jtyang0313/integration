package com.yjt.config.mq;


import java.util.UUID;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.yjt.entity.po.Company;

/**
 * 运抵单消息队列发送
 */
@Slf4j
@Component
public class CompanySender implements RabbitTemplate.ConfirmCallback {

	@Resource(name="company")
	private RabbitTemplate rabbitTemplate;
	
	public void sendMsg(Company company) {
		this.rabbitTemplate.setConfirmCallback(this);
		String sendStr=JSON.toJSONString(company);
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend("ex_oms_arrival","send",sendStr,correlationData);
		log.info("运抵单队列发送消息：{}",company);
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		log.info("消息唯一标识："+correlationData);
		if(ack){
			log.info("消息发送成功！");
		}else{
			log.info("消息发送失败！失败原因：{}"+cause);
		}
	}

}
