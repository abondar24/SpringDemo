package org.abondar.experimental.springsecurity.service;

import org.abondar.experimental.springsecurity.model.UserData;
import org.abondar.experimental.springsecurity.util.PasswordUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void addNewUserTest(){
        var usr= new UserData("test","test");
        userService.addOrUpdateStore(usr);

        var res = userService.find(usr.login());

        assertTrue(res.isPresent());
        assertEquals(usr.login(),res.get().login());
    }

    @Test
    public void addExistingTest(){
        var usr= new UserData("test","test");
        userService.addOrUpdateStore(usr);

        usr= new UserData("test","test1");
        userService.addOrUpdateStore(usr);

        var res = userService.find(usr.login());

        assertTrue(res.isPresent());
        assertEquals(usr.login(),res.get().login());

        var pwdValid = PasswordUtil.verifyPassword("test1",res.get().password());
        assertTrue(pwdValid);
    }

    @Test
    public void deleteUserTest(){
        var usr= new UserData("test","test");
        userService.addOrUpdateStore(usr);

        userService.delete(usr.login());

        var res = userService.find(usr.login());
        assertTrue(res.isEmpty());
    }
}
