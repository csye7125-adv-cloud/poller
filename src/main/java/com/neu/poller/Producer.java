package com.neu.poller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import com.neu.poller.model.Alert;
import com.neu.poller.model.FieldType;
import com.neu.poller.model.Operator;
import com.neu.poller.model.Watch;
import com.neu.poller.model.Weather;

@Component
public class Producer {
	@Autowired
	private KafkaTemplate<String, Weather> kafkaTemplate;
	@Autowired
    MeterRegistry registry;

    Timer kafkaTimer;

	public void sendMessage(Weather weather) {
//		Watch watch=new Watch();
//		watch.setWatch_id(UUID.randomUUID().toString());
//		watch.setUser_id(UUID.randomUUID().toString());
//		watch.setZipcode(zipcode);
//		Alert alert=new Alert();
//		alert.setAlert_id(UUID.randomUUID().toString());
//		alert.setField_type(FieldType.temp);
//		alert.setOperator(Operator.gt);
//		alert.setValue(20);
//		List<Alert> alerts=new ArrayList<Alert>();
//		alerts.add(alert);
//		watch.setAlerts(alerts);
	//	weather.setWatch(null);
	//weather.setCurrent_weather(nu);
	kafkaTimer = registry.timer("custom.metrics.timer", "Poller", "Kafka_Weather_Produce");
    registry.counter("custom.metrics.counter", "Poller", "Weather_messages_produced").increment();
	ListenableFuture<SendResult<String, Weather>> future = 
	      kafkaTimer.record(()->kafkaTemplate.send("weather", weather));
	    System.out.println("weather posted");
	}
}
