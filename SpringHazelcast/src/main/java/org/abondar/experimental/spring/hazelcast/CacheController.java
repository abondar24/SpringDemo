package org.abondar.experimental.spring.hazelcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private CacheService cacheService;


    @Autowired
    public CacheController(CacheService cacheService){
        this.cacheService=cacheService;
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
       person = cacheService.addPerson(person);
       return ResponseEntity.ok(person);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findPerson(@PathVariable String id){
        var person = cacheService.findPerson(id);

        if (person.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(person.get());
    }

}
