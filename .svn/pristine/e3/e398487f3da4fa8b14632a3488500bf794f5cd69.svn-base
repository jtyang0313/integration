package com.yjt.config.mq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

	@Value("${spring.rabbitmq.host}")
	private String host;

	@Value("${spring.rabbitmq.port}")
	private String port;

	@Value("${spring.rabbitmq.username}")
	private String userName;

	@Value("${spring.rabbitmq.virtual-host}")
	private String virtualHost;

	@Value("${spring.rabbitmq.password}")
	private String password;
	
	@Value("${spring.rabbitmq.prefetchCount}")
	private Integer prefetchCount;
	
	@Value("${spring.rabbitmq.concurrentConsumers}")
	private Integer concurrentConsumers;
	
	@Value("${spring.rabbitmq.maxConcurrency}")
	private Integer maxConcurrency;
	

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(host + ":" + port);
		connectionFactory.setUsername(userName);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		connectionFactory.setPublisherConfirms(true); // 必须要设置
		return connectionFactory;
	}

	@Bean(name = "firstFactory")
	public SimpleRabbitListenerContainerFactory secondFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
			ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
		configurer.configure(factory, connectionFactory);
		factory.setPrefetchCount(prefetchCount);
		factory.setConcurrentConsumers(concurrentConsumers);
		factory.setMaxConcurrentConsumers(maxConcurrency);
		return factory;
	}

	@Bean(name = "company")
	public RabbitTemplate arrivalTemplate() {
		RabbitTemplate arrivalTemplate = new RabbitTemplate(connectionFactory());
		return arrivalTemplate;
	}

	/**
	 * 交换机绑定队列
	 * 
	 * @return
	 */
	@Bean
	public Binding companyBinding() {
		return BindingBuilder.bind(companyQueue()).to(arrivalExchange()).with("send");
	}

	/**
	 * 交换器
	 * 
	 * @return
	 */
	@Bean
	public DirectExchange arrivalExchange() {
		return new DirectExchange("ex_oms_arrival");
	}

	/**
	 * 接受企业备案队列
	 * 
	 * @return
	 */
	@Bean
	public Queue companyQueue() {
		return new Queue("yjtCompany_send", true);
	}

}
