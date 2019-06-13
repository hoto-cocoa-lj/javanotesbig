package com.pyg.manager.utils;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //相当于 applicationContext.xml
public class PygConfugration {
	
	//在spring配置文件中有多个bean
	//<bean class="对象">
	@Bean
	public ActiveMQTopic getTopic(){
		//创建一个对象
		ActiveMQTopic topic = new ActiveMQTopic("mytopic");
		
		return topic;
	}

}
