package com.neu.poller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.neu.poller.model.Watch;
import com.neu.poller.openweather.OpenWeatherMap;
import com.neu.poller.repository.WatchDao;
import com.neu.poller.service.WatchService;
import com.neu.poller.service.WeatherService;

@Component
public class WatchConsumer {
	@Autowired
	OpenWeatherMap weatherapi;
	@Autowired
	WatchService watchservice;
	@Autowired
	WeatherService weatherService;
	@KafkaListener(topics = "watch")
	public void listenWithHeaders(
	  @Payload Watch watch, 
	  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		watchservice.AddWatch(watch);
		weatherService.triggerWeatherApi();
           
	}

}
