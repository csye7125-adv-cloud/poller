package com.neu.poller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.neu.poller.controller.HealthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.poller.model.Alert;
import com.neu.poller.model.AlertTopicModel;
import com.neu.poller.model.Watch;
import com.neu.poller.model.WatchTopicModel;
import com.neu.poller.openweather.OpenWeatherMap;
import com.neu.poller.repository.WatchDao;
import com.neu.poller.service.WatchService;
import com.neu.poller.service.WeatherService;

@Component
public class WatchConsumer {

	private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

	@Autowired
	OpenWeatherMap weatherapi;
	@Autowired
	WatchService watchservice;
	@Autowired
	WeatherService weatherService;
	@KafkaListener(topics = "watch")
	public void listenWithHeaders(
	  @Payload WatchTopicModel topicModel, 
	  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {

		System.out.println("Watch event received");
		logger.info("Poller App - Watch event received");
		logger.info("****Poller App - Consumed message with id "+ topicModel.getWatch_id() + " from topic watch and Partition ID "+ partition + "*****");
		if(topicModel.getAction().equals("CREATED")||topicModel.getAction().equals("UPDATED")) {
		Watch watch=new Watch();
		watch.setWatch_id(topicModel.getWatch_id());
		watch.setZipcode(topicModel.getZipcode());
		watch.setUser_id(topicModel.getUser_id());
		List<Alert> alerts=new ArrayList<Alert>();
		for(AlertTopicModel alertTopic:topicModel.getAlerts()) {
			Alert alert=new Alert();
			alert.setAlert_id(alertTopic.getAlert_id());
			alert.setField_type(alertTopic.getField_type());
			alert.setOperator(alertTopic.getOperator());
			alert.setValue(alertTopic.getValue());
			alert.setAlert_updated(alertTopic.getAlert_updated());
			alert.setAlert_created(alertTopic.getAlert_created());
			alerts.add(alert);
		}
		watch.setAlerts(alerts);
		watchservice.AddWatch(watch);
		weatherService.triggerWeatherApi("UPDATED");
		logger.info("Poller App- Message published on weather kafka topic by User with user id " + topicModel.getUser_id());
		}
		if(topicModel.getAction().equals("DELETED")) {
			weatherService.triggerWeatherApi("DELETED");
			logger.info("Poller App- On weather kafka topic - Message with id " + topicModel.getWatch_id()+ " deleted");
			watchservice.DeleteWatch(topicModel.getWatch_id());
			
			}
           
	}

}
