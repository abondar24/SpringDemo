package org.abondar.experimental.graphserver;

import org.abondar.experimental.graphserver.config.GraphConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({GraphConfiguration.class})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}
