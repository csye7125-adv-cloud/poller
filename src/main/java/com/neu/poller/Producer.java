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

import com.neu.poller.model.Alert;
import com.neu.poller.model.FieldType;
import com.neu.poller.model.Operator;
import com.neu.poller.model.Watch;

//@Component
public class Producer {
//	@Autowired
//	private KafkaTemplate<String, Watch> kafkaTemplate;
//
//	public void sendMessage(String zipcode) {
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
//	    ListenableFuture<SendResult<String, Watch>> future = 
//	      kafkaTemplate.send("watch", watch);
//	}
}
