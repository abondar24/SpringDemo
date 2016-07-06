package org.abondar.experimental.HelloWorld;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

/**
 * Created by abondar on 02.07.16.
 */

public class HelloWorldRun {

    static Logger logger = LoggerFactory.getLogger(HelloWorldRun.class);

    public static void main(String[] args) {

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
