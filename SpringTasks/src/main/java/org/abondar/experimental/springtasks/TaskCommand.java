package org.abondar.experimental.springtasks;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.Future;

/**
 * Created by abondar on 18.07.16.
 */
public class TaskCommand {
    private static Logger logger = LoggerFactory.getLogger(TaskCommand.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(TaskConfig.class);
        ctx.refresh();

        AsyncService asyncService = ctx.getBean("asyncService", AsyncService.class);
        for (int i = 0; i < 7; i++) {
            asyncService.asyncTask();
        }

        Future<String> res1 = asyncService.asyncWithReturn("Bender");
        Future<String> res2 = asyncService.asyncWithReturn("Fry");
        Future<String> res3 = asyncService.asyncWithReturn("Leela");

        try {
            Thread.sleep(7000);
            logger.info("Res1: " + res1.get());
            logger.info("Res2: " + res2.get());
            logger.info("Res3: " + res3.get());

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        TaskToExecute taskToExecute = ctx.getBean(TaskToExecute.class);
        taskToExecute.executeTask();
    }
}
