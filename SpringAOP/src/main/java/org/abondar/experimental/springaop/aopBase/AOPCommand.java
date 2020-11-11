package org.abondar.experimental.springaop.aopBase;


import org.abondar.experimental.springdemo.command.Command;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by abondar on 16.07.16.
 */
public class AOPCommand implements Command {

    public void run() {

        MessageWriter target = new MessageWriter();

        //around advice(comment before to work)
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new MessageDecorator());
        MessageWriter proxy = (MessageWriter) proxyFactory.getProxy();
        proxy.writeMessage();

        //before advice
        proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new SimpleBeforeAdvice());
        MessageWriter beforeProxy = (MessageWriter) proxyFactory.getProxy();
        beforeProxy.writeMessage();

        //after-returning
        proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new SimpleAfterReturningAdvice());
        MessageWriter afterProxy = (MessageWriter) proxyFactory.getProxy();
        afterProxy.writeMessage();

        //throws-advice
        proxyFactory = new ProxyFactory();
        ErrorBean errorBean = new ErrorBean();
        proxyFactory.setTarget(errorBean);
        proxyFactory.addAdvice(new SimpleThrowsAdvice());

        ErrorBean errorProxy = (ErrorBean) proxyFactory.getProxy();

        try {
            errorProxy.errorProneMethod();
        } catch (Exception ex) {
        }

        try {
            errorProxy.otherErrorProneMetod();
        } catch (Exception ex) {
        }

    }
}
