package org.abondar.experimental.springsecurity.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.abondar.experimental.springsecurity.model.UserCreateRequest;
import org.abondar.experimental.springsecurity.model.UserResponse;
import org.abondar.experimental.springsecurity.service.UserService;
import org.abondar.experimental.springsecurity.util.EndpointUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(EndpointUtil.SECURITY_PATH)
public class SecurityController {

    private final UserService service;

    @Autowired
    public SecurityController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "User successfully created"))
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        var resp = service.addOrUpdateStore(userCreateRequest);

        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "Login user",security = { @SecurityRequirement(name = "basicScheme") })
    @PostMapping(path = EndpointUtil.LOGIN_PATH)
    public ResponseEntity<String> loginUser() {

        return ResponseEntity.ok("logged in");
    }


    @Operation(summary = "Refresh expired JWT token",security = { @SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "JWT refreshed and set to header"))
    @PostMapping(path = EndpointUtil.REFRESH_PATH)
    public ResponseEntity<String> refreshToken() {

        return ResponseEntity.ok("Token refreshed");
    }


    @Operation(summary = "Get secret data",security = { @SecurityRequirement(name = "bearer-key"),@SecurityRequirement(name = "oauth-key") })
    @GetMapping
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<String> getSecret() {

        return ResponseEntity.ok("Secret");
    }

    @Operation(summary = "Get ultra secret data",security = { @SecurityRequirement(name = "bearer-key"),@SecurityRequirement(name = "oauth-key") })
    @GetMapping(path = EndpointUtil.ULTRA_PATH)
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> getUltraSecret() {

        return ResponseEntity.ok("Ultrasecret");
    }

    @Operation(summary = "Delete existing user",security = { @SecurityRequirement(name = "bearer-key"),@SecurityRequirement(name = "oauth-key") })
    @DeleteMapping(path = EndpointUtil.ID_PATH)
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("User deleted");
    }


}
