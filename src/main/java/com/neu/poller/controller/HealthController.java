package com.neu.poller.controller;


import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neu.poller.repository.HealthDAO;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController
public class HealthController {

    @Autowired
    HealthDAO healthdao;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @RequestMapping(value = "/readyStatus", method = RequestMethod.GET)
    public ResponseEntity<?> redinessProbe() {
        try {

            Properties properties = new Properties();
            properties.put("bootstrap.servers", bootstrapAddress);
            properties.put("connections.max.idle.ms", 10000);
            properties.put("request.timeout.ms", 5000);
            try (AdminClient client = KafkaAdminClient.create(properties)) {
                ListTopicsResult topics = client.listTopics();
                Set<String> names = topics.names().get();
                if (names.isEmpty())
                {
                    // case: if no topic found. But server is available
                }
            } catch (InterruptedException | ExecutionException e) {
                return new ResponseEntity<>("KAFKA_SERVICE_UNAVAILABLE ", HttpStatus.SERVICE_UNAVAILABLE);
            }
            int status = healthdao.findDbConnectivityStatus();
            if(status == 1) {
                return ResponseEntity.ok("Application is Ready");
            } else {
                return new ResponseEntity<>("DB_SERVICE_UNAVAILABLE ", HttpStatus.SERVICE_UNAVAILABLE);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("SERVICE_UNAVAILABLE ", HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

    @RequestMapping(value = "/healthStatus", method = RequestMethod.GET)
    public ResponseEntity<?> livenessProbe() {
        try {
            Properties properties = new Properties();
            properties.put("bootstrap.servers", bootstrapAddress);
            properties.put("connections.max.idle.ms", 10000);
            properties.put("request.timeout.ms", 5000);
            try (AdminClient client = KafkaAdminClient.create(properties)) {
                ListTopicsResult topics = client.listTopics();
                Set<String> names = topics.names().get();
                if (names.isEmpty())
                {
                    // case: if no topic found. But server is available
                }
            } catch (InterruptedException | ExecutionException e) {
                return new ResponseEntity<>("KAFA_UNAVAILABLE_APPLICATION_UNHEALTHY ", HttpStatus.SERVICE_UNAVAILABLE);
            }
            int status = healthdao.findDbConnectivityStatus();
            if(status == 1) {
                return ResponseEntity.ok("Application is Healthy");
            } else {
                return new ResponseEntity<>("DB_UNAVAILABLE_APPLICATION_UNHEALTHY ", HttpStatus.SERVICE_UNAVAILABLE);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("APPLICATION_UNHEALTHY ", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}

