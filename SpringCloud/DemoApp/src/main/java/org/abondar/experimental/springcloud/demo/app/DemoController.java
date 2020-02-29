package org.abondar.experimental.springcloud.demo.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cloud")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> getApplication(){

        return ResponseEntity.ok("Demo Application in cloud");
    }
}
