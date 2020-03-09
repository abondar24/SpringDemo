package org.abondar.experimental.springcloud.demo.kafka.client.redis;


import org.abondar.experimental.springcloud.demo.kafka.client.stream.DemoMessage;

public interface RedisCacheRepository {

    void saveMessage(DemoMessage message);
    DemoMessage findMessage(String id);
}
