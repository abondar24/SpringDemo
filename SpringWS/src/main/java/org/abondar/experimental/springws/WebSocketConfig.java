package org.abondar.experimental.springws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * Created by abondar on 23.07.16.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

//doesn't work with sock js in parallel with stomp
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //enable sockjs if client doesn't support ws
        webSocketHandlerRegistry.addHandler(echoHandler(),"/echoHandler")
        .addInterceptors(new HttpSessionHandshakeInterceptor())
                .setAllowedOrigins("*");

    }

    @Bean
    public EchoHandler echoHandler(){
return new EchoHandler();
    }
}
