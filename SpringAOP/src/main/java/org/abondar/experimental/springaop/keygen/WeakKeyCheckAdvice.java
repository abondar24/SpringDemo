package org.abondar.experimental.springaop.keygen;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by abondar on 16.07.16.
 */
public class WeakKeyCheckAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object retVal, Method method, Object[] args, Object target) throws Throwable {
        if ((target instanceof KeyGenerator) && (method.getName().equals("getKey"))){
            long key = ((Long) retVal).longValue();

            if (key == KeyGenerator.WEAK_KEY){
                throw new SecurityException("Generated a weak key");
            }
        }
    }
}
