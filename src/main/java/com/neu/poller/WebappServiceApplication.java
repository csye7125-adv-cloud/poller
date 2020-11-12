package com.neu.poller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ComponentScan(basePackages = "com.neu.poller")
@SpringBootApplication
@EnableJpaAuditing
@EnableAutoConfiguration(excludeName = {"org.springframework.boot.actuate.autoconfigure.metrics.KafkaMetricsAutoConfiguration"})

public class WebappServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(WebappServiceApplication.class, args);


		
	}

}
