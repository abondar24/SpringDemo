package org.abondar.experimental.springsecurity.service;

import org.abondar.experimental.springsecurity.model.UserCreateRequest;
import org.abondar.experimental.springsecurity.util.PasswordUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void addNewUserTest(){
        var usr= new UserCreateRequest("test","test", List.of());

        var resp = userService.addOrUpdateStore(usr);
        assertNotNull(resp);
        assertTrue(resp.id().matches("([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})"));

        var res = userService.find(resp.id());

        assertTrue(res.isPresent());
        assertEquals(usr.login(),res.get().login());
    }

    @Test
    public void addExistingTest(){
        var usr= new UserCreateRequest("test","test",List.of());
        userService.addOrUpdateStore(usr);

        var resp =userService.addOrUpdateStore(usr);

        var res = userService.find(resp.id());
        assertTrue(res.isPresent());
        assertEquals(res.get().id(),resp.id());
        assertEquals(usr.login(),res.get().login());

        var pwdValid = PasswordUtil.verifyPassword("test",res.get().hash());
        assertTrue(pwdValid);
    }

    @Test
    public void findUserTest(){
        var usr = new UserCreateRequest("test", "test", List.of());
        var resp = userService.addOrUpdateStore(usr);

        var foundById = userService.find(resp.id());
        var foundByName = userService.findByUsername(usr.login());

        assertTrue(foundById.isPresent());
        assertTrue(foundByName.isPresent());
        assertEquals(foundById.get().login(),foundByName.get().login());
    }

    @Test
    public void deleteUserTest() {
        var usr = new UserCreateRequest("test", "test", List.of());
        var resp = userService.addOrUpdateStore(usr);

        userService.delete(resp.id());

        var res = userService.find(resp.id());
        assertTrue(res.isEmpty());
    }
}
