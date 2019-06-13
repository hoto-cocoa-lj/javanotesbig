package com.pyg.manager.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PygController {

	@RequestMapping("/hello")
	public String showHello() {
		return "hello ,springboot";
	}

	// 模拟通过浏览器发送消息
	@Autowired
	private JmsTemplate jmsTemplate;

	// 发送消息
	// 点对点模式
	@RequestMapping("/jms")
	public void sendMessage(String text) {
		// 1,如果没有指定消息发送类型: 默认是点对点类型
		// 2,如果没有指定acitiveMQ消息服务地址,将会使用springboot提供内置mq
		// 发送消息
		jmsTemplate.convertAndSend("itcast", text);
	}
	// 发送消息
	// 点对点模式
	@RequestMapping("/ps")
	public void sendMessageByPs() {
		List<String> ids = new ArrayList<String>();
		ids.add("101010101");
		
		// 1,如果没有指定消息发送类型: 默认是点对点类型
		// 2,如果没有指定acitiveMQ消息服务地址,将会使用springboot提供内置mq
		// 发送消息
		jmsTemplate.convertAndSend("solrTopic","为什么就是接受不了消息");
	}
}
