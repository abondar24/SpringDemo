package org.abondar.experimental.springdemo.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveApp {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveApp.class,args);

        DemoClient dc = new DemoClient();
        System.out.println(dc.getResult());
        System.out.println(dc.postGreeting("Hiiii",128));
    }
}
