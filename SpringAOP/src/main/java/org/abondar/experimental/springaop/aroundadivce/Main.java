package org.abondar.experimental.springaop.aroundadivce;

import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by abondar on 16.07.16.
 */
public class Main {
    public static void main(String[] args) {
      WorkerBean bean = getWorkerBean();
        bean.doWork(1000000);
    }

    private static WorkerBean getWorkerBean(){
        WorkerBean target = new WorkerBean();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(new ProfilingInterceptor());

        return (WorkerBean)factory.getProxy();
    }
}
