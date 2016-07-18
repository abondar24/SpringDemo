package org.abondar.experimental.springaop.aopBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by abondar on 16.07.16.
 */
public class SimpleAfterReturningAdvice implements AfterReturningAdvice{

    private Logger logger = LoggerFactory.getLogger(SimpleAfterReturningAdvice.class);

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        logger.info("After method: "+method.getName());
    }
}
