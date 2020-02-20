package org.abondar.experimental.springboot.kafka.controller;

import org.abondar.experimental.springboot.kafka.service.DemoConsumer;
import org.abondar.experimental.springboot.kafka.service.DemoProducer;
import org.abondar.experimental.springboot.kafka.service.HealthService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController("/kafka-demo")
public class DemoController {

    private DemoProducer producer;

    private DemoConsumer consumer;

    private HealthService healthService;

    @Autowired
    public DemoController(DemoProducer producer, DemoConsumer consumer,HealthService healthService) {
        this.producer = producer;
        this.consumer = consumer;
        this.healthService = healthService;
    }


    @PutMapping("/send")
    public void sendMessage(@RequestBody String message) {
          producer.sendMessage(message);

    }

    @GetMapping("/messages")
    public ResponseEntity<List<String>> getMessages(){

        return ResponseEntity.ok(consumer.getMessages());
    }

    @GetMapping("/health")
    public ResponseEntity<Boolean> health(){

        return ResponseEntity.ok(healthService.isKafkaHealthy());
    }

}
