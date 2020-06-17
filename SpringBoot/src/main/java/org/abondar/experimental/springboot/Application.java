package org.abondar.experimental.springboot;

import org.abondar.experimental.springboot.auth.AuthConfig;
import org.abondar.experimental.springboot.swagger.SwaggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by abondar on 25.07.16.
 */

@Configuration
@Import({AuthConfig.class, SwaggerConfig.class})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
