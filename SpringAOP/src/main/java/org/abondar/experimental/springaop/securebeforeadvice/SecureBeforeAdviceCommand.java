package org.abondar.experimental.springaop.securebeforeadvice;

import org.abondar.experimental.springdemo.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by abondar on 16.07.16.
 */
public class SecureBeforeAdviceCommand implements Command {
    static Logger logger = LoggerFactory.getLogger(SecurityAdvice.class);

    public  void run() {
        SecurityManager securityManager = new SecurityManager();
        SecureBean secureBean = getSecureBean();

        securityManager.login("Alex", "password");
        secureBean.writeSecureMessage();
        securityManager.logout();

        try {
            securityManager.login("George", "password");
            secureBean.writeSecureMessage();

        } catch (SecurityException ex) {
            logger.info("Exception caught: " + ex.getMessage());
        } finally {
            securityManager.logout();
        }

        try {
            secureBean.writeSecureMessage();
        } catch (SecurityException ex) {
            logger.info("Exception caught: " + ex.getMessage());
        } finally {
            securityManager.logout();
        }

    }


    private static  SecureBean getSecureBean(){
        SecureBean target = new SecureBean();
        SecurityAdvice advice = new SecurityAdvice();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(advice);
        SecureBean proxy = (SecureBean)proxyFactory.getProxy();

        return proxy;
    }
}
