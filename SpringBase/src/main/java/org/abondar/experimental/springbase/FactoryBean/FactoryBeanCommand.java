package org.abondar.experimental.springbase.FactoryBean;


import org.abondar.experimental.springdemo.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by abondar on 02.07.16.
 */

public class FactoryBeanCommand implements Command {
    static Logger logger = LoggerFactory.getLogger(FactoryBeanCommand.class);

    public  void run() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(FactoryBeanConfiguration.class);

        ctx.refresh();

        MessageDigester digester = ctx.getBean(MessageDigester.class);
        digester.digest("Hello");


    }


}
