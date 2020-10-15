package com.neu.poller.openweather;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neu.poller.WeatherProducer;
import com.neu.poller.model.Watch;
import com.neu.poller.model.Weather;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

@Component
public class OpenWeatherMap {
//	@Autowired
//	WeatherProducer weatherProducer;
	private static String apiBase = "http://api.openweathermap.org/data/2.5/weather?";
	public static JSONObject fetch(String zipcode,String apiKey) throws IOException, InterruptedException {
	    String apiUrl = apiBase + "zip="+zipcode + "&appid=" + apiKey + "&mode=json&units=metric";

	HttpClient client = HttpClient.newBuilder()
		      .build();
	HttpRequest request = HttpRequest.newBuilder()
		      .uri(URI.create(apiUrl))
		      .header("Content-Type", "application/json")
		      .GET()
		      .build();
	HttpResponse<String> response =
		      client.send(request,BodyHandlers.ofString());
	 JSONObject obj = new JSONObject(response.body());
	    return obj;
	}
	 
	    public void init(Watch watch) throws IOException, InterruptedException {

		        JSONObject obj = null;
				try {
					obj = fetch(watch.getZipcode(), "14578452155f59ef8911325c99afdf48");
					
					Weather weather=new Weather();
					weather.setCurrent_weather(obj);
					weather.setWatch(watch);
					
		//			weatherProducer.sendMessage(weather);
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        System.out.println(obj);
	    
     
	    }
	

}
