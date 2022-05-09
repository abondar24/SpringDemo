package org.abondar.experimental.springsecurity.service;

import org.abondar.experimental.springsecurity.model.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        var token = service.generateToken("123","test", List.of("test"));

        assertNotNull(token);
        assertFalse(token.isBlank());
    }

    @Test
    public void validateTokenTest(){
        var req = new UserRequest("test","test",List.of("test"));
        var resp = userService.addOrUpdateStore(req);
        var data = userService.find(resp.id());

        var token = service.generateToken(resp.id(),data.get().hash(),req.roles());
        var isValid = service.validateToken(token);

        assertTrue(isValid);
    }


}
