package org.abondar.experimental.springaop.aopBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Created by abondar on 16.07.16.
 */
public class SimpleThrowsAdvice implements ThrowsAdvice{
    private Logger logger = LoggerFactory.getLogger(SimpleThrowsAdvice.class);

    public void afterThrowing(Exception ex){
        logger.info("Generic exception capture");
        logger.info("Caught: "+ex.getClass().getName());
    }


    public void afterThrowing(Method method, Object[] args, Object target,
                              IllegalArgumentException ex) throws Throwable {
        logger.info("IllegalArgumentException capture");
        logger.info("Caught: "+ex.getClass().getName());
        logger.info("Method: "+method.getName());

    }

}
