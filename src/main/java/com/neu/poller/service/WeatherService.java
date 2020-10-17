package com.neu.poller.service;

public interface WeatherService {
	//UpdateWatchInfo
	void triggerWeatherApi(String action);

	void callApiInit();

}
