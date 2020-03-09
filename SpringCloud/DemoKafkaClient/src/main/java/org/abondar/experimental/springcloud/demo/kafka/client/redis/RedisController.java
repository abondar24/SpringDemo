package org.abondar.experimental.springcloud.demo.kafka.client.redis;

import org.abondar.experimental.springcloud.demo.kafka.client.stream.DemoMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private RedisCacheRepository cacheRepository;

    @Autowired
    public RedisController(RedisCacheRepository cacheRepository) {
        this.cacheRepository = cacheRepository;
    }

    @GetMapping("/find/{msgId}")
    public ResponseEntity<DemoMessage> findMessage(@PathVariable String msgId){
        DemoMessage res = cacheRepository.findMessage(msgId);

        return ResponseEntity.ok(res);
    }
}
