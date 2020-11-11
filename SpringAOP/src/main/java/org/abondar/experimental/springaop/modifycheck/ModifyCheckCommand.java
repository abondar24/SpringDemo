package org.abondar.experimental.springaop.modifycheck;

import org.abondar.experimental.springaop.securebeforeadvice.SecurityAdvice;
import org.abondar.experimental.springdemo.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by abondar on 18.07.16.
 */
public class ModifyCheckCommand implements Command {
    static Logger logger = LoggerFactory.getLogger(SecurityAdvice.class);

    public  void run() {
        TargetBean target = new TargetBean();
        target.setName("Ned Stark");

        IntroductionAdvisor advisor = new IsModifiedAdvisor();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setOptimize(true);

        TargetBean proxy = (TargetBean)proxyFactory.getProxy();
        IsModified proxyInterface = (IsModified)proxy;

        logger.info("Is TargetBean?: "+ (proxy instanceof TargetBean));
        logger.info("Is IsModified?: "+ (proxy instanceof IsModified));

        logger.info("Has been modified?: "+ proxyInterface.isModified());
        proxy.setName("Ned Stark");

        logger.info("Has been modified?: "+ proxyInterface.isModified());
        proxy.setName("John Snow");

        logger.info("Has been modified?: "+ proxyInterface.isModified());

    }
}
