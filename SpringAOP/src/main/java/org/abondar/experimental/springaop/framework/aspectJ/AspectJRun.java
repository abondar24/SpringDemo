package org.abondar.experimental.springaop.framework.aspectJ;

import org.abondar.experimental.springaop.aopBase.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by abondar on 18.07.16.
 */
public class AspectJRun {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AspectConfig.class);
        ctx.refresh();

        AspectBean bean = ctx.getBean(AspectBean.class);
        bean.execute();

    }
}
