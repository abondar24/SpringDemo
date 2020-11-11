package org.abondar.experimental.springbase.MessageEvent;


import org.abondar.experimental.springdemo.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by abondar on 02.07.16.
 */

public class MessageEventCommand implements Command {

    static Logger logger = LoggerFactory.getLogger(MessageEventCommand.class);

    @Override
    public void run() {
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
            ctx.register(MessageEventConfiguration.class);
            ctx.refresh();

            Publisher publisher = ctx.getBean(Publisher.class);

            publisher.setApplicationContext(ctx);
            publisher.publish("YA SVOBODEN");
            publisher.publish("YA ZABIL CHTO ZNCHIT STRAH");


    }
}
