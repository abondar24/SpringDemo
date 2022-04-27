package org.abondar.experimental.springsecurity.service;

import org.abondar.experimental.springsecurity.util.PasswordUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordUtilTest {

    @Test
    public void testCreateHash(){
        var pwd = "test";
        var res = PasswordUtil.createHash(pwd);

        assertTrue(res.contains("sha1"));

    }

    @Test
    public void testVerifyPwd(){
        var pwd = "test";
        var hash = PasswordUtil.createHash(pwd);
        var res = PasswordUtil.verifyPassword(pwd,hash);

        assertTrue(res);

    }
}
