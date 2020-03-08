package org.abondar.experimental.springcloud.demo.kafka.client.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
public class DemoDestination {

    Logger logger = LoggerFactory.getLogger(DemoDestination.class);

    @StreamListener(Sink.INPUT)
    public void listenAndLog(DemoMessage message){
           logger.info(message.toString());
    }
}
