package org.abondar.experimental.springaop.framework;

import org.abondar.experimental.springaop.modifycheck.IsModified;
import org.abondar.experimental.springaop.modifycheck.TargetBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by abondar on 18.07.16.
 */
public class Main {
    private static  Logger logger = LoggerFactory.getLogger(MyAdvice.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MyConfiguration.class);
        context.refresh();

        MyBean bean1 = context.getBean("bean1",MyBean.class);
        MyBean bean2 = context.getBean("bean2",MyBean.class);

        logger.info("Bean 1");
        bean1.execute();

        logger.info("Bean 2");
        bean2.execute();

        //introduction with framework support
        TargetBean bean = context.getBean("introductionBean", TargetBean.class);
        IsModified mod = (IsModified) bean;

        logger.info("Is TargetBean?: " + (bean instanceof TargetBean));
        logger.info("Is IsModified?: " + (bean instanceof IsModified));

        logger.info("Has been modified?: " + mod.isModified());
        bean.setName("Anton Spak");

        logger.info("Has been modified?: " + mod.isModified());
        bean.setName("Chris Schaefer");

        logger.info("Has been modified?: " + mod.isModified());

    }
}
