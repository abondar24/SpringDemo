package org.abondar.experimental.springsecurity.controller;

import org.abondar.experimental.springsecurity.model.UserCreateRequest;
import org.abondar.experimental.springsecurity.model.UserResponse;
import org.abondar.experimental.springsecurity.service.JwtService;
import org.abondar.experimental.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;


//TODO: oauth2 integration
//TODO: swagger integration
//TODO: make constant util
//TODO: test jwt expiration + enable refresh token
//TODO: multifactor auth integaration
@RestController
@RequestMapping("/security")
public class SecurityController {

    private final UserService service;

    @Autowired
    public SecurityController(UserService service) {
        this.service = service;
    }


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        var resp = service.addOrUpdateStore(userCreateRequest);

        return ResponseEntity.ok(resp);
    }

    @PostMapping(
            path = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> loginUser() {

        return ResponseEntity.ok("logged in");
    }


    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed("user")
    public ResponseEntity<String> getSecret() {

        return ResponseEntity.ok("Secret");
    }

    @GetMapping(
            path = "/ultra",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed("admin")
    public ResponseEntity<String> getUltraSecret() {

        return ResponseEntity.ok("Ultrasecret");
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.of(Optional.of("User deleted"));
    }


}
