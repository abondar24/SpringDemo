package org.abondar.experimental.springws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * Created by abondar on 23.07.16.
 */
public class EchoHandler extends TextWebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(EchoHandler.class);

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException{
       logger.info(message.getPayload());
        session.sendMessage(new TextMessage(message.getPayload()));

    }
}
