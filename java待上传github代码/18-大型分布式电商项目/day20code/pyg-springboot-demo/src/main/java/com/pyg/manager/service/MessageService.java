package com.pyg.manager.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Component;

@Component
public class MessageService {

	// 监听消息空间
	@JmsListener(destination = "itcast")
	public void receiveMessage(String text) {
		System.out.println("接受消息:" + text);
	}

	// 监听消息空间
	@JmsListener(destination="mytopic")
	public void receiveMessageByTopic(String text) {
		System.out.println("接受消息:" + text);
	}

}
