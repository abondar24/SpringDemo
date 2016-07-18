package org.abondar.experimental.springaop.securebeforeadvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by abondar on 16.07.16.
 */
public class SecurityAdvice implements MethodBeforeAdvice {

    private Logger logger = LoggerFactory.getLogger(SecurityAdvice.class);

    private SecurityManager securityManager;

    public SecurityAdvice() {
        securityManager = new SecurityManager();
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        UserInfo user = securityManager.getLoggedOnUser();

        if (user == null) {
            logger.info("No user authenticated");
            throw new SecurityException("You must login before invocating method "+method.getName());
        } else if (user.getUserName().equals("Alex")){
            logger.info("Logged in user is Alex - OK!");
        } else {
            logger.info("Logged in user is " + user.getUserName() +" NOT GOOD");
            throw new SecurityException("User " + user.getUserName() +
                    " has no access to method " +method.getName());
        }
    }
}
