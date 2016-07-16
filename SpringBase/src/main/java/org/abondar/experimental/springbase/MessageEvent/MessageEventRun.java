package org.abondar.experimental.springbase.MessageEvent;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by abondar on 02.07.16.
 */

public class MessageEventRun {

    static Logger logger = LoggerFactory.getLogger(MessageEventRun.class);

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(MessageEventConfiguration.class);
        ctx.refresh();

        Publisher publisher = ctx.getBean(Publisher.class);

        publisher.setApplicationContext(ctx);
        publisher.publish("YA SVOBODEN");
        publisher.publish("YA ZABIL CHTO ZNCHIT STRAH");


    }

}
