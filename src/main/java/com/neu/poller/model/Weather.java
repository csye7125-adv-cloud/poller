package com.neu.poller.model;

import org.json.JSONObject;

public class Weather {
	private WatchTopicModel watch;
    private WeatherModel weather;
    private String action;
	public WatchTopicModel getWatch() {
		return watch;
	}
	public void setWatch(WatchTopicModel watch) {
		this.watch = watch;
	}
	public WeatherModel getWeather() {
		return weather;
	}
	public void setWeather(WeatherModel weather) {
		this.weather = weather;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
    
	


		

}
