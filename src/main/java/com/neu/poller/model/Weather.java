package com.neu.poller.model;

import org.json.JSONObject;

public class Weather {
	private Watch watch;
	private JSONObject current_weather;

	public JSONObject getCurrent_weather() {
		return current_weather;
	}
	public void setCurrent_weather(JSONObject current_weather) {
		this.current_weather = current_weather;
	}
	public Watch getWatch() {
		return watch;
	}
	public void setWatch(Watch watch) {
		this.watch = watch;
	}
	
	
	

}
