package org.abondar.experimental.BeanLifeCycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by abondar on 03.07.16.
 */

@Configuration
public class BeanLifeCycleConfiguration {

    @Bean
    public SimpleBean simpleBean1() {
        SimpleBean sb = new SimpleBean();
        sb.setName("Vasya Pukin");
        sb.setAge(32);
        return sb;
    }


    @Bean
    public SimpleBean simpleBean2() {
        SimpleBean sb = new SimpleBean();
        sb.setName("Lord Volan De Mort");
        sb.setAge(46);
        return sb;
    }

    @Bean
    public SimpleBean simpleBean3() {
        SimpleBean sb = new SimpleBean();
        sb.setAge(62);
        return sb;
    }

    @Bean

    public SimpleDestuctiveBean simpleDestuctiveBean(){
        return new SimpleDestuctiveBean();
    }
}
