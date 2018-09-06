package org.abondar.experimental.springdemo.reactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class DemoRouter {

    @Bean
    public RouterFunction<ServerResponse> router(DemoHandler handler){
        return RouterFunctions.route(RequestPredicates.GET("/hello")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handler::hello)
                .andRoute(RequestPredicates.POST("/post_greeting")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handler::postGreeting);
    }
}
