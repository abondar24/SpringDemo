package org.abondar.experimental.spring.hazelcast;



import com.hazelcast.core.HazelcastInstance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith({SpringExtension.class})
@SpringBootTest
public class CacheServiceTest {

    @Autowired
    private CacheService cacheService;


    @Test
    public void cacheTest(){
        var person = new Person("Alex","8-800-535-55-35");
        person = cacheService.addPerson(person);

        var res = cacheService.findPerson(person.getId());

        assertFalse(res.isEmpty());
        assertEquals(person.getId(),res.get().getId());
}
}
