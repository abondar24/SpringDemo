package org.abondar.experimental.springsecurity.service;

import org.abondar.experimental.springsecurity.model.UserCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class JwtServiceTest {

    @Autowired
    private JwtService service;

    @Autowired
    private UserService userService;


    @Test
    public void generateTokenTest(){
        var req = new UserCreateRequest("test","test",List.of("test"));
        var resp = userService.addOrUpdateStore(req);
        var data = userService.find(resp.id());
        var token = service.generateToken(data.get());

        assertNotNull(token);
        assertFalse(token.isBlank());
    }

    @Test
    public void validateTokenTest(){
        var req = new UserCreateRequest("test","test",List.of("test"));
        var resp = userService.addOrUpdateStore(req);
        var data = userService.find(resp.id());
        var token = service.generateToken(data.get());

        var authentication = service.parseAndValidateToken(token);

        assertTrue(authentication.isPresent());
        assertEquals(data.get().login(), authentication.get().getPrincipal());
    }


}
