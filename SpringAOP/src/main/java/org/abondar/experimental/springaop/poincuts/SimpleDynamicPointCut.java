package org.abondar.experimental.springaop.poincuts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * Created by abondar on 16.07.16.
 */
public class SimpleDynamicPointCut extends DynamicMethodMatcherPointcut {
    private Logger logger = LoggerFactory.getLogger(SimpleDynamicPointCut.class);


    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return method.getName().equals("foo");
    }

    @Override
    public boolean matches(Method method, Class<?> aClass, Object[] args) {
       logger.info("Static check for "+method.getName());
        int x = ((Integer)args[0]).intValue();
        return x!=100;
    }

    public ClassFilter getClassFilter(){
        return aClass -> aClass == SampleBean.class;
    }
}
