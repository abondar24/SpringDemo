package org.abondar.experimental.springsecurity.controller;

import org.abondar.experimental.springsecurity.model.UserRequest;
import org.abondar.experimental.springsecurity.model.UserResponse;
import org.abondar.experimental.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/security")
public class SecurityController {

    private final UserService service;

    @Autowired
    public SecurityController(UserService service){
        this.service = service;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        var resp =service.addOrUpdateStore(userRequest);
        return ResponseEntity.of(Optional.of(resp));
    }


    @GetMapping(
            path = "/secret/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSecret(@PathVariable String id){

        return ResponseEntity.of(Optional.of("Secret"));
    }

    @GetMapping(
            path="/ultra/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<String> getUltraSecret(@PathVariable String id){

        return ResponseEntity.of(Optional.of("Ultrasecret"));
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.of(Optional.of("User deleted"));
    }


}