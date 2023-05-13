package com.example.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;


@Configuration
public class KafkaProducerConfig {
	@Value("${spring.kafka.producer.bootstrap-servers}")
	private String bootstrapServers;
	@Value("${spring.kafka.producer.properties.security.protocol}")
	private String securityProtocol;
	@Value("${spring.kafka.producer.properties.sasl.mechanism}")
	private String mechanism;
	@Value("${spring.kafka.producer.properties.sasl.jaas.config}")
	private String jasConfig;
	@Value("${spring.kafka.producer.properties.sasl.client.callback.handler.class}")
	private String callBackHandler;
	@Bean
	public ProducerFactory<String,Object> producerFactory()
	{
		Map<String,Object> configs=new HashMap<>();
		
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
		configs.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, securityProtocol);
		configs.put("sasl.mechanism", mechanism);
		configs.put("sasl.jaas.config", jasConfig);
		configs.put("sasl.client.callback.handler.class", callBackHandler);
		return new DefaultKafkaProducerFactory<String,Object>(configs);
	}
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate()
	{
		return new KafkaTemplate<>(producerFactory());
	}
}
