package org.abondar.experimental.springbase.MessageEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

/**
 * Created by abondar on 06.07.16.
 */
public class MessageEventListener implements ApplicationListener<MessageEvent> {
    static Logger logger = LoggerFactory.getLogger(MessageEventListener.class);

    @Override
    public void onApplicationEvent(MessageEvent event) {
        MessageEvent messageEvent = (MessageEvent) event;
        logger.info("Received: "+messageEvent.getMsg());

    }
}
