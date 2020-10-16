package com.neu.poller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

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
		weatherService.triggerWeatherApi();
		}
		if(topicModel.getAction().equals("DELETED")) {
			watchservice.DeleteWatch(topicModel.getWatch_id());
			
			}
           
	}

}
