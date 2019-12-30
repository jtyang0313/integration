package com.yjt.config.mq;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.yjt.entity.po.Company;

/**
 * 消息队列接收类
 */
@Slf4j
@Component
public class CompanyMqReceiver {

	@RabbitListener(queues = "yjtCompany_send")
	@RabbitHandler
	public void process(@Payload String contentStr, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel)
			throws Exception {

		boolean issuc = true;
		try {

			JSONObject jsonObject = JSONObject.parseObject(contentStr);
			log.info("=====接收到运抵回执数据打印===== content={}", jsonObject);
			Company arrivalReceipt = jsonObject.toJavaObject(Company.class);

			log.info("=====运抵数据打印===== Company={}", JSON.toJSON(arrivalReceipt));

		} catch (Exception e) {
			log.error("接收失败:" + e.getMessage());
		} finally {
			if (issuc) {
				log.info("消费成功");
				channel.basicAck(deliveryTag, false);// 确认消息成功消费
			} else {
				log.info("消费失败");
				channel.basicNack(deliveryTag, false, true);
			}
		}
	}
}
