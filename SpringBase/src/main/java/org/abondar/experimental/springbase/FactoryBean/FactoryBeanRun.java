package org.abondar.experimental.springbase.FactoryBean;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by abondar on 02.07.16.
 */

public class FactoryBeanRun {
    static Logger logger = LoggerFactory.getLogger(FactoryBeanRun.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(FactoryBeanConfiguration.class);

        ctx.refresh();

        MessageDigester digester = ctx.getBean(MessageDigester.class);
        digester.digest("Hello");


    }


}
