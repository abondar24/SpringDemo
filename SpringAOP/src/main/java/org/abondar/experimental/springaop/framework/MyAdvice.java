package org.abondar.experimental.springaop.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by abondar on 16.07.16.
 */
public class MyAdvice implements MethodBeforeAdvice{
    private Logger logger = LoggerFactory.getLogger(MyAdvice.class);

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        logger.info("Exectuing: "+method);
    }
}
