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
import java.net.http.HttpClient;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.neu.poller.Producer;
import com.neu.poller.WeatherProducer;
import com.neu.poller.model.Alert;
import com.neu.poller.model.AlertTopicModel;
import com.neu.poller.model.Watch;
import com.neu.poller.model.WatchTopicModel;
import com.neu.poller.model.Weather;
import com.neu.poller.model.WeatherModel;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

@Component
public class OpenWeatherMap {
	@Value("${open.api.key}")
	public String openApiKey;
	@Autowired
WeatherProducer weatherProducer;
	@Autowired
Producer producer;
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
	 
	    public void init(Watch watch,String action) throws IOException, InterruptedException {

		        JSONObject obj = null;
				try {
//					obj = fetch(watch.getZipcode(), "14578452155f59ef8911325c99afdf48");
//					obj = fetch(watch.getZipcode(), "7c3837c39e630c278d83d7956db4258d");
					obj = fetch(watch.getZipcode(), openApiKey);
					
					Weather weather=new Weather();
					String json=obj.get("main").toString();
					Gson g = new Gson();
					WeatherModel p=null;
					try {
					p = g.fromJson(json, WeatherModel.class);
					}
					catch(Exception e) {
						System.out.println(e.toString());
					}
                    
				//	weather.setCurrent_weather(obj);
			//		Object temp=new JSONObject(obj.get("main"));
				//	weather.setTemp(Integer.valueOf(temp.get("temp").toString()));
					WatchTopicModel topicModel=new WatchTopicModel();
					
					topicModel.setWatch_id(watch.getWatch_id());
					topicModel.setZipcode(watch.getZipcode());
					topicModel.setUser_id(watch.getUser_id());
					List<AlertTopicModel> alerts=new ArrayList<AlertTopicModel>();
					for(Alert alert:watch.getAlerts()) {
						AlertTopicModel alerttopic=new AlertTopicModel();
						alerttopic.setAlert_id(alert.getAlert_id());
						alerttopic.setField_type(alert.getField_type());
						alerttopic.setOperator(alert.getOperator());
						alerttopic.setValue(alert.getValue());
						alerttopic.setAlert_updated(alert.getAlert_updated());
						alerttopic.setAlert_created(alert.getAlert_created());
						alerts.add(alerttopic);
					}
					topicModel.setAlerts(alerts);
					weather.setWatch(topicModel);
					weather.setWeather(p);
					weather.setAction(action);
				
//					weather.setWatch(null);
				//weather.setCurrent_weather(obj);
					producer.sendMessage(weather);
					//weatherProducer.sendMessage(weather);
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        System.out.println(obj);
	    
     
	    }
	

}
