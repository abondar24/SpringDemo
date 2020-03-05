package org.abondar.experimental.springcloud.demo.app;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/cloud")
public class DemoController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping
    public ResponseEntity<String> getApplication(){

        return ResponseEntity.ok("Demo Application in cloud");
    }

    @GetMapping("/discovery/{app}")
    public List<ServiceInstance> serviceInstanceByApp(@PathVariable String app){
        return discoveryClient.getInstances(app);
    }

    @GetMapping("/hystrix/circuit")
    @HystrixCommand(
            fallbackMethod = "fallBack",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "22000")
            }
    )
    public String demoCircuit(){
        randomFailure();
        return  "ok";
    }

    @GetMapping("/hystrix/fallback")
    @HystrixCommand(
            fallbackMethod = "fallBack",
            threadPoolKey = "fallBackThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value = "10"),
                    @HystrixProperty(name = "maxQueueSize",value = "5")
            },
            commandProperties={
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "15000")
            }
    )
    public String demoFallBack(){
        randomFailure();
        return  "ok";
    }


    private String fallBack(){
        return "fallback";
    }

    private void randomFailure(){
        Random random = new Random();

        int num = random.nextInt((7-1)+1)+1;

        if (num==7){
            sleep();
        }
    }

    private void sleep(){
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e){
            System.out.println(e);
        }
    }

}
