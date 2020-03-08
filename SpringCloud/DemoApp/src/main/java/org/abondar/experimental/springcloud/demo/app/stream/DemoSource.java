package org.abondar.experimental.springcloud.demo.app.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class DemoSource {

    private Source source;

    @Autowired
    public DemoSource(Source source) {
        this.source = source;
    }

    @SendTo(Source.OUTPUT)
    public DemoMessage publishMessage(String body) {
        DemoMessage demoMessage = new DemoMessage(body);

        source.output().send(MessageBuilder.withPayload(demoMessage).build());

        return demoMessage;
    }
}
