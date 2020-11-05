package com.neu.poller.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.neu.poller.controller.HealthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.poller.model.Watch;
import com.neu.poller.openweather.OpenWeatherMap;

@Service
public class WeatherServiceImpl implements WeatherService {
	@Autowired
	WatchService watchService;
	@Autowired
	OpenWeatherMap map;

	private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

	@Override
	public void callApiInit() {

		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		// Thread.currentThread().interrupt();
		Runnable task = () -> {
			triggerWeatherApi("");
             
		};

		executor.scheduleWithFixedDelay(task, 0, 5, TimeUnit.MINUTES);
	}

	@Override
	public void triggerWeatherApi(String action) {
		// TODO Auto-generated method stub
		List<Watch> watches = watchService.getWatches();

		for (Watch watch : watches) {

			try {
				map.init(watch,action);
			   
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
