package org.abondar.experimental.springaop.aopBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by abondar on 16.07.16.
 */
public class SimpleBeforeAdvice implements MethodBeforeAdvice{
    private Logger logger = LoggerFactory.getLogger(SimpleBeforeAdvice.class);

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        logger.info("Before method: "+method.getName());
    }
}
