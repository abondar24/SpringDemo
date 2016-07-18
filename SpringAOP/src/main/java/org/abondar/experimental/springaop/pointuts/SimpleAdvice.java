package org.abondar.experimental.springaop.pointuts;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by abondar on 16.07.16.
 */
public class SimpleAdvice implements MethodInterceptor{

    private Logger logger = LoggerFactory.getLogger(SimpleAdvice.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        logger.info("Invoking " +methodInvocation.getMethod().getName());
        Object retVal = methodInvocation.proceed();
        logger.info("Done.");

        return retVal;
    }
}
