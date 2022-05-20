package org.abondar.experimental.springsecurity.service;

import org.abondar.experimental.springsecurity.model.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        var req = new UserRequest("test","test",List.of("test"));
        var resp = userService.addOrUpdateStore(req);
        var token = service.generateToken(resp.id());

        assertNotNull(token);
        assertFalse(token.isBlank());
    }

    @Test
    public void validateTokenTest(){
        var req = new UserRequest("test","test",List.of("test"));
        var resp = userService.addOrUpdateStore(req);

        var token = service.generateToken(resp.id());
        var authentication = service.parseAndValidateToken(token);

        assertTrue(authentication.isPresent());
        assertEquals(resp.id(), authentication.get().getPrincipal());
    }


}
