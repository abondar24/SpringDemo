package org.abondar.experimental.springcloud.demo.app;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
