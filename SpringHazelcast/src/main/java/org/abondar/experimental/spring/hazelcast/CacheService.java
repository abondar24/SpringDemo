package org.abondar.experimental.spring.hazelcast;


import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CacheService {


    private final List<Person> storage = new ArrayList<>();

    public Person addPerson(Person person){
        var id = UUID.randomUUID().toString();
        person.setId(id);
        storage.add(person);

        return person;
    }

    @Cacheable("person")
    public Optional<Person> findPerson(String id){
        return storage.stream()
                .filter(p->p.getId().equals(id))
                .findFirst();
    }


}
