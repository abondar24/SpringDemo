package org.abondar.experimental.springboot.controller;

import org.abondar.experimental.springboot.exception.DemoException;
import org.abondar.experimental.springboot.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by abondar on 25.07.16.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping(method= RequestMethod.GET)
    public String demo() {
      return "Spring Boot REST demo";
    }

    @GetMapping(path = "/id")
    public ResponseEntity<String> idDemo(@RequestParam(value="id") long id){
        return ResponseEntity.ok( id +" accepted");
    }

    @PostMapping(path = "/person/{id}")
    public ResponseEntity<Person> postDemo(@RequestBody Person person, @PathVariable  long id){
       person.setId(id);

        return ResponseEntity.ok(person);
    }

    @PutMapping(path = "/ex/{name}")
    public ResponseEntity<Boolean> exceptionDemo(@PathVariable  String name) throws DemoException{

        if (name.equals("Arsen")){
            throw new DemoException();
        }

        return ResponseEntity.ok(true);
    }

}
