package org.abondar.experimental.springcloud.demo.kafka.client.stream;

import org.abondar.experimental.springcloud.demo.kafka.client.redis.RedisCacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
public class DemoDestination {

    private Logger logger = LoggerFactory.getLogger(DemoDestination.class);

    private RedisCacheRepository cacheRepository;

    @Autowired
    public DemoDestination(RedisCacheRepository cacheRepository){
        this.cacheRepository = cacheRepository;
    }

    @StreamListener(Sink.INPUT)
    public void listenAndLog(DemoMessage message){
           logger.info(message.toString());
           cacheRepository.saveMessage(message);
    }
}
