package org.abondar.experimental.springaop.proxies;

import org.abondar.experimental.springaop.pointuts.SampleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * Created by abondar on 17.07.16.
 */
public class ProxyTest {
    private static Logger logger = LoggerFactory.getLogger(SampleBean.class);

    public static void main(String[] args) {
        SimpleBean target = new DummySimpleBean();

        Advisor advisor = new DefaultPointcutAdvisor(new TestPointCut(), new NoOpBeforeAdvice());

        runCglibTests(advisor,target);
        runCglibFrozenTests(advisor,target);
        runJdkTests(advisor,target);
    }

    private static void runCglibTests(Advisor advisor,SimpleBean target){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);

        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        logger.info("Running CGLIB(Standard) Tests");
        test(proxy);
    }

    private static void runCglibFrozenTests(Advisor advisor,SimpleBean target){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setFrozen(true);

        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        logger.info("Running CGLIB(Frozen) Tests");
        test(proxy);
    }

    private static void runJdkTests(Advisor advisor,SimpleBean target){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setInterfaces(new Class[]{SimpleBean.class});

        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        logger.info("Running JDK Tests");
        test(proxy);
    }


    private static void test(SimpleBean bean){
        long before = 0;
        long after = 0;

        logger.info("Testing Advised method");
        before = System.currentTimeMillis();
        for (int x =0; x<500000;x++){
            bean.advised();
        }
        after = System.currentTimeMillis();
        logger.info("Took "+(after - before)+ " ms");

        logger.info("Testing Unadvised method");
        before = System.currentTimeMillis();
        for (int x =0; x<500000;x++){
            bean.unadvised();
        }
        after = System.currentTimeMillis();
        logger.info("Took "+(after - before)+ " ms");

        logger.info("Testing Equals method");
        before = System.currentTimeMillis();
        for (int x =0; x<500000;x++){
            bean.equals(bean);
        }
        after = System.currentTimeMillis();
        logger.info("Took "+(after - before)+ " ms");


        logger.info("Testing Hashcode method");
        before = System.currentTimeMillis();
        for (int x =0; x<500000;x++){
            bean.hashCode();
        }
        after = System.currentTimeMillis();
        logger.info("Took "+(after - before)+ " ms");

        Advised advised = (Advised) bean;
        logger.info("Testing Advised.getProxyTarget method");
        before = System.currentTimeMillis();
        for (int x =0; x<500000;x++){
            advised.getTargetClass();
        }
        after = System.currentTimeMillis();
        logger.info("Took "+(after - before)+ " ms");

    }

}
