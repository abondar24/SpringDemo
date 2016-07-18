package org.abondar.experimental.springaop.aopBase;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by abondar on 16.07.16.
 */

//aop advice
public class MessageDecorator implements MethodInterceptor{
   private Logger logger = LoggerFactory.getLogger(MessageDecorator.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        logger.info("Hello ");
        Object retVal = methodInvocation.proceed();
        logger.info("!");
        return retVal;

    }
}
