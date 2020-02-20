package org.abondar.experimental.springboot.kafka.service;

import org.abondar.experimental.springboot.kafka.util.KafkaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DemoProducer {

    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    public DemoProducer(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message){
        kafkaTemplate.send(KafkaUtil.KAFKA_TOPIC,message);
    }

}
