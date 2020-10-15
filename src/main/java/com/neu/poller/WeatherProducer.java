package com.neu.poller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.neu.poller.model.Alert;
import com.neu.poller.model.FieldType;
import com.neu.poller.model.Operator;
import com.neu.poller.model.Watch;
import com.neu.poller.model.Weather;

@Component
public class WeatherProducer {
	@Autowired
	private KafkaTemplate<String, Weather> kafkaTemplate;

	public void sendMessage(Weather weather) {

	    ListenableFuture<SendResult<String, Weather>> future = 
	      kafkaTemplate.send("weather", weather);
	}
}
