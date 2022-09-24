package com.example.kafka;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaConsumerApplication {
	
	User userFromTopic=null;
	List<String> messages=new ArrayList<>();
	
	
	@GetMapping("/consumerStringMsg")
	public List<String> consumerMsg()
	{
		return messages;
	}

	@GetMapping("/consumerJsonMsg")
	public User consumerJsonMsg()
	{
		return userFromTopic;
	}
	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}
	
	@KafkaListener(groupId="kafka-consumer-1",topics="kafkatopic1",containerFactory="kafkaListenerContainerFactory")
	public List<String> getMsgfromTopic(String data)
	{
		messages.add(data);
		return messages;
	}
	@KafkaListener(groupId="kafka-consumer-2",topics="kafkatopic1",containerFactory="userkafkaListenerContainerFactory")
	public User getJsonMsgfromTopic(User user)
	{
		userFromTopic=user;
		return userFromTopic;
	}

}
