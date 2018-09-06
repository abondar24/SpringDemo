package org.abondar.experimental.springdemo.reactive;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class DemoClient {

    private WebClient client = WebClient.create("http://localhost:8080");

    private Mono<ClientResponse> result = client.get()
            .uri("/hello")
            .accept(MediaType.APPLICATION_JSON)
            .exchange();




    public String getResult(){
        return "Get result == " + result.flatMap(res -> res.bodyToMono(HelloObject.class)).block();
    }

    public String postGreeting(String greeting,int id){
        HelloObject helloObject = new HelloObject(id,greeting);
        Mono<ClientResponse> postResult = client.post()
                .uri("/post_greeting")
                .body(BodyInserters.fromObject(helloObject))
                .accept(MediaType.APPLICATION_JSON)
                .exchange();

        return "Post result == " + postResult.flatMap(res->res.bodyToMono(Integer.class)).block();
    }

}
