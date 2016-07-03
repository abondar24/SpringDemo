package org.abondar.experimental.HelloWorld;


import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by abondar on 02.07.16.
 */

public class HelloWorldRun {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(Config.class);
        ctx.refresh();

        //dependency pull
        MessageRenderer mr = ctx.getBean(MessageRenderer.class);
        mr.render();

        MessageProvider mp = ctx.getBean("messageProvider",MessageProvider.class);
        System.out.println(mp.getMessage());

        MessageProvider mp1 = ctx.getBean("messageProviderConf",MessageProvider.class);
        System.out.println(mp1.getMessage());

        SimpleBean sb1 = getSimpleBean("simpleBean1",ctx);
        System.out.println(sb1.toString());

        SimpleBean sb2 = getSimpleBean("simpleBean2",ctx);
        System.out.println(sb2.toString());

        SimpleBean sb3 = getSimpleBean("simpleBean3",ctx);
        System.out.println(sb3.toString());

    }

    private static SimpleBean getSimpleBean(String beanName,AnnotationConfigApplicationContext ctx){
        try {
            SimpleBean bean = (SimpleBean) ctx.getBean(beanName);
            return bean;
        } catch (BeanCreationException ex){
            System.out.println("Error in bean config "+ex.getMessage());
            return null;
        }
    }
}
