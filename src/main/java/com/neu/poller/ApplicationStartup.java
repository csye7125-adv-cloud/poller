package com.neu.poller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.neu.poller.service.WeatherService;

@Component
public class ApplicationStartup 
implements ApplicationListener<ApplicationReadyEvent> {

  /**
   * This event is executed as late as conceivably possible to indicate that 
   * the application is ready to service requests.
   */
	@Autowired
	WeatherService weatherservice;
  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {

    weatherservice.callApiInit();

    return;
  }

}
