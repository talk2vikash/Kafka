package com.example.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaProducerApplication {

	@Autowired
	private KafkaTemplate<String, Object> template;
	
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}
	
	@GetMapping("/publish/{name}")
	public String publishString(@PathVariable String name)
	{
		template.send("kafkatopic1","Hi " + name + " welcome to kafka producer");
		return "Data published";
	}
	@GetMapping("/publishJson")
	public String publishJson()
	{
		User user =new User(1245,"Vikash",new String[]{"room","street"});
		template.send("kafkatopic1",user);
		return "Data published";
	}

}
