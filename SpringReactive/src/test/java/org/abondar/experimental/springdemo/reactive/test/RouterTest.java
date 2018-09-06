package org.abondar.experimental.springdemo.reactive.test;

import org.abondar.experimental.springdemo.reactive.HelloObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouterTest {

    @Autowired
    private WebTestClient testClient;

    @Test
    public void helloTest(){
        HelloObject res =testClient
                .get()
                .uri("/hello")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(HelloObject.class).returnResult().getResponseBody();

        assertEquals("",24,res.getId());
        assertEquals("","Hello World",res.getGreeting());

    }

    @Test
    public void postTest(){
        Integer res =testClient
                .post()
                .uri("/post_greeting")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(new HelloObject(24,"PRIVET")))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Integer.class).returnResult().getResponseBody();

        assertEquals("",24,res);
    }
}
