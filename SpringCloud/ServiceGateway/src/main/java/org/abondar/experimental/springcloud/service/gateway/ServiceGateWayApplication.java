package org.abondar.experimental.springcloud.service.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ServiceGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGateWayApplication.class,args);
    }
}
