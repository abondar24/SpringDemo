package org.abondar.experimental.springaop.proxies;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by abondar on 17.07.16.
 */
public class NoOpBeforeAdvice implements MethodBeforeAdvice{
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {

    }
}
