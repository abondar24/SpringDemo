package org.abondar.experimental.springcloud.demo.kafka.client.redis;

import org.abondar.experimental.springcloud.demo.kafka.client.stream.DemoMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class RedisCacheRepositoryImpl implements RedisCacheRepository {

    private static final String HASH_NAME = "somehash";

    private RedisTemplate<String,Object> redisTemplate;
    private HashOperations<String,String,Object> hashOperations;

    @Autowired
    public RedisCacheRepositoryImpl(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        this.hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public void saveMessage(DemoMessage message) {
        hashOperations.put(HASH_NAME,message.getId(),message);
    }

    @Override
    public DemoMessage findMessage(String id) {

        return (DemoMessage) hashOperations.get(HASH_NAME,id);
    }
}
