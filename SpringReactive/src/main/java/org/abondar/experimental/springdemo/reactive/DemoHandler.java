package org.abondar.experimental.springdemo.reactive;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class DemoHandler {

    private static Mono<?> apply(HelloObject post) {
        System.out.println("Created a new greeting: " + post);

        return Mono.just(post.getId());
    }

    public Mono<ServerResponse> hello(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(new HelloObject(24,"Hello World")));
    }



    public Mono<ServerResponse> postGreeting(ServerRequest request){
        return request.bodyToMono(HelloObject.class)
                .flatMap(DemoHandler::apply)
                .flatMap(pid->ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(pid)));
    }


}
