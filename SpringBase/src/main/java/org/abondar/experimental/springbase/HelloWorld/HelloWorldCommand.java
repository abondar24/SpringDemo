package org.abondar.experimental.springbase.HelloWorld;



import org.abondar.experimental.springdemo.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by abondar on 02.07.16.
 */

public class HelloWorldCommand implements Command {

    static Logger logger = LoggerFactory.getLogger(HelloWorldCommand.class);

    public void run() {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(HelloWorldConfiguration.class);
        ctx.refresh();

        //dependency pull
        MessageRenderer mr = ctx.getBean(MessageRenderer.class);
        mr.render();

        MessageProvider mp = ctx.getBean("messageProvider", MessageProvider.class);
        logger.info(mp.getMessage());

        MessageProvider mp1 = ctx.getBean("messageProviderConf", MessageProvider.class);
        logger.info(mp1.getMessage());
    }

}
