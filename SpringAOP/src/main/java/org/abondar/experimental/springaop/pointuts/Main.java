package org.abondar.experimental.springaop.pointuts;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

/**
 * Created by abondar on 16.07.16.
 */
public class Main {
    public static void main(String[] args) {
        invokeStatic();
        invokeDynamic();
        invokeNamed();
        invokeRegexp();
        invokeAspectjExp();
        invokeAnnotated();
    }

    private static void invokeStatic() {
        BeanOne one = new BeanOne();
        BeanTwo two = new BeanTwo();

        BeanOne proxyOne;
        BeanTwo proxyTwo;

        Pointcut pointcut = new SimpleStaticPointCut();
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(one);
        proxyOne = (BeanOne) proxyFactory.getProxy();

        proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(two);
        proxyTwo = (BeanTwo) proxyFactory.getProxy();

        proxyOne.foo();
        proxyTwo.foo();

        proxyOne.bar();
        proxyOne.bar();

    }

    private static void invokeDynamic() {
        SampleBean target = new SampleBean();
        Advisor advisor = new DefaultPointcutAdvisor(new SimpleDynamicPointCut(), new SimpleAdvice());
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(target);
        SampleBean proxy = (SampleBean) proxyFactory.getProxy();

        proxy.foo(1);
        proxy.foo(10);
        proxy.foo(100);

        proxy.bar();
        proxy.bar();
        proxy.bar();


    }

    private static void invokeNamed() {
        NamedBean target = new NamedBean();

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("foo");
        pointcut.addMethodName("bar");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        NamedBean proxy = (NamedBean) proxyFactory.getProxy();

        proxy.foo();
        proxy.foo(1000);
        proxy.bar();
        proxy.yup();
    }

    private static void invokeRegexp() {
        RegexpBean target = new RegexpBean();

        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*foo.*");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        RegexpBean proxy = (RegexpBean) proxyFactory.getProxy();

        proxy.foo1();
        proxy.foo2();
        proxy.bar();
    }

    private static void invokeAspectjExp() {
        RegexpBean target = new RegexpBean();

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo*(..))");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        RegexpBean proxy = (RegexpBean) proxyFactory.getProxy();

        proxy.foo1();
        proxy.foo2();
        proxy.bar();
    }

    private static void invokeAnnotated() {
        SampleAnnotationBean target = new SampleAnnotationBean();

        AnnotationMatchingPointcut pointcut = AnnotationMatchingPointcut
                .forMethodAnnotation(AdviceRequired.class);
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        SampleAnnotationBean proxy = (SampleAnnotationBean) proxyFactory.getProxy();

        proxy.foo(100);
        proxy.bar();

    }


}
