package org.abondar.experimental.springbase.BeanLifeCycle;


import org.abondar.experimental.springdemo.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by abondar on 02.07.16.
 */

public class BeanLifeCycleCommand implements Command {
    static Logger logger = LoggerFactory.getLogger(BeanLifeCycleCommand.class);

    public void run() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(BeanLifeCycleConfiguration.class);
        ctx.registerShutdownHook();
        ctx.refresh();

        SimpleBean sb1 = getSimpleBean("simpleBean1",ctx);
        logger.info(sb1.toString());

        SimpleBean sb2 = getSimpleBean("simpleBean2",ctx);
        logger.info(sb2.toString());

        SimpleBean sb3 = getSimpleBean("simpleBean3",ctx);
        logger.info(sb3.toString());

        SimpleDestuctiveBean sdb = ctx.getBean(SimpleDestuctiveBean.class);


    }

    private static SimpleBean getSimpleBean(String beanName,AnnotationConfigApplicationContext ctx){
        try {
            SimpleBean bean = (SimpleBean) ctx.getBean(beanName);
            return bean;
        } catch (BeanCreationException ex){
            logger.info("Error in bean config "+ex.getMessage());
            return null;
        }
    }
}
