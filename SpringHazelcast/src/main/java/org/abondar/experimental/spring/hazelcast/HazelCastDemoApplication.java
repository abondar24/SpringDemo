package org.abondar.experimental.spring.hazelcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class HazelCastDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HazelCastDemoApplication.class);
    }
}
