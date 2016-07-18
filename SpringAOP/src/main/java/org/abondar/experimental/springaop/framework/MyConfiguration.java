package org.abondar.experimental.springaop.framework;

import org.abondar.experimental.springaop.modifycheck.IsModifiedAdvisor;
import org.abondar.experimental.springaop.modifycheck.TargetBean;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abondar on 18.07.16.
 */

@Configuration
public class MyConfiguration {

    @Bean

    public MyBean bean1() {
        MyBean bean1 = new MyBean();
        bean1.setDep((MyDependency) dep1().getObject());
        return bean1;
    }

    @Bean
    public MyBean bean2() {
        MyBean bean2 = new MyBean();
        bean2.setDep((MyDependency) dep2().getObject());
        return bean2;
    }

    @Bean
    public MyDependency myDependencyTarget() {
        return new MyDependency();
    }


    @Bean
    public ProxyFactoryBean dep1() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(myDependencyTarget());
        proxyFactoryBean.addAdvice(advice());
        return proxyFactoryBean;
    }


    @Bean
    public ProxyFactoryBean dep2() {


        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(myDependencyTarget());
        proxyFactoryBean.addAdvisor(advisor());
        return proxyFactoryBean;
    }


    @Bean
    public MyAdvice advice() {
        return new MyAdvice();
    }

    @Bean
    public DefaultPointcutAdvisor advisor() {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice(advice());
        advisor.setPointcut(expressionPointcut());
        return advisor;
    }

    @Bean
    public AspectJExpressionPointcut expressionPointcut() {
        AspectJExpressionPointcut expressionPointcut = new AspectJExpressionPointcut();
        expressionPointcut.setExpression("execution(* foo*(..))");
        return expressionPointcut;
    }


    @Bean
    ProxyFactoryBean introductionBean(){
        ProxyFactoryBean bean = new ProxyFactoryBean();
        bean.setTarget(targetBean());
        bean.addAdvisor(isModifiedAdvisor());
        bean.setProxyTargetClass(true);
    return bean;
    }

    @Bean
    TargetBean targetBean(){
        TargetBean targetBean = new TargetBean();
        targetBean.setName("Vasya Pupkin");
        return targetBean;

    }

    @Bean
    IsModifiedAdvisor isModifiedAdvisor(){
        return new IsModifiedAdvisor();
    }
}
