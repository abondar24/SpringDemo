package org.abondar.experimental.springaop.poincuts;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * Created by abondar on 16.07.16.
 */
public class SimpleStaticPointCut extends StaticMethodMatcherPointcut{
    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return method.getName().equals("foo");
    }

    @Override
    public ClassFilter getClassFilter(){
        return aClass -> aClass == BeanOne.class;
    }

}
