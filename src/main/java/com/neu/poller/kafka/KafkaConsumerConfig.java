package com.neu.poller.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.neu.poller.model.Watch;
import com.neu.poller.model.WatchTopicModel;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	  @Value("${spring.kafka.bootstrap-servers}")
	  private String bootstrapAddress;
	  @Value("${spring.kafka.bootstrap-servers}")
	  private String groupId;
	 @Bean
	    public ConsumerFactory<String, WatchTopicModel> consumerFactory() {
	        Map<String, Object> props = new HashMap();
	        props.put(
	          ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
	          bootstrapAddress);
	        props.put(
	                ConsumerConfig.GROUP_ID_CONFIG, 
	                groupId);
	        props.put(
	          ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
	          StringDeserializer.class);
	        props.put(
	          ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
	          JsonDeserializer.class);
	        
		    return new DefaultKafkaConsumerFactory(
				      props,
				      new StringDeserializer(), 
				      new JsonDeserializer(WatchTopicModel.class,false));
	        
	    }
	 
	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, WatchTopicModel> 
	      kafkaListenerContainerFactory() {
	   
		    ConcurrentKafkaListenerContainerFactory<String, WatchTopicModel> factory =
				      new ConcurrentKafkaListenerContainerFactory();
				    factory.setConsumerFactory(consumerFactory());
				    return factory;
	    }

}
