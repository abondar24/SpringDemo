package org.abondar.experimental.springboot.kafka.service;

import org.abondar.experimental.springboot.kafka.util.KafkaUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoConsumer {

    private List<String> messages = new ArrayList<>();


    @KafkaListener(topics = KafkaUtil.KAFKA_TOPIC, groupId = "spring.kafka.consumer.group-id")
    public void consume(String message) {
        System.out.println(message);
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
