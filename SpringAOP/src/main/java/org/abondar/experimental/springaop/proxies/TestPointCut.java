package org.abondar.experimental.springaop.proxies;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * Created by abondar on 17.07.16.
 */
public class TestPointCut extends StaticMethodMatcherPointcut{
    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return method.getName().equals("advised");
    }
}
