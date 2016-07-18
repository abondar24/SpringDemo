package org.abondar.experimental.springaop.modifycheck;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by abondar on 18.07.16.
 */
public class IsModifiedMixin extends DelegatingIntroductionInterceptor implements IsModified {

    private Map<Method, Method> methodCache = new HashMap<>();
    private Boolean isModified = false;

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (!isModified) {
            if (invocation.getMethod().getName().startsWith("set")
                    && invocation.getArguments().length == 1) {
                Method getter = getGetter(invocation.getMethod());

                if (getter != null) {
                    Object newVal = invocation.getArguments();
                    Object oldVal = getter.invoke(invocation.getThis(), null);

                    if (newVal == null && oldVal == null) {
                        isModified = false;
                    } else if (newVal == null && oldVal != null) {
                        isModified = true;
                    } else if (newVal != null && oldVal == null) {
                        isModified = true;
                    } else {
                        isModified = !newVal.equals(oldVal);
                    }
                }
            }
        }
        return super.invoke(invocation);
    }

   private Method getGetter(Method setter){
       Method getter =null;
       getter = (Method) methodCache.get(setter);

       if (getter!=null){
           return getter;
       }

       String getterName = setter.getName().replaceFirst("set","get");

       try {
           getter = setter.getDeclaringClass().getMethod(getterName,null);
           synchronized (methodCache){
               methodCache.put(setter,getter);
           }
           return getter;
       } catch (NoSuchMethodException ex){
           return null;
       }
   }
}
