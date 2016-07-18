package org.abondar.experimental.springaop.aroundadivce;

import org.abondar.experimental.springaop.aopBase.MessageDecorator;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

/**
 * Created by abondar on 16.07.16.
 */
public class ProfilingInterceptor implements MethodInterceptor {
    private Logger logger = LoggerFactory.getLogger(MessageDecorator.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(methodInvocation.getMethod().getName());

        Object returnVal = methodInvocation.proceed();

        stopWatch.stop();

        dumpInfo(methodInvocation, stopWatch.getTotalTimeMillis());
        return returnVal;

    }


    private void dumpInfo(MethodInvocation invocation,long ms){
        Method m = invocation.getMethod();
        Object target = invocation.getThis();
        Object[]args = invocation.getArguments();

        logger.info("Executed method: " + m.getName());
        logger.info("On object of type: "+target.getClass().getName());
        logger.info("With arguments:" );
        for (int x=0;x<args.length;x++){
            logger.info("    >" +args[x]);
        }
        logger.info("\n");
        logger.info("Took: "+ms +" ms");

    }
}
