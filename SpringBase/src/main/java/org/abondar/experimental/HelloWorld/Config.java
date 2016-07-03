package org.abondar.experimental.HelloWorld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by abondar on 03.07.16.
 */

@Configuration
public class Config {

    @Bean
    public MessageRenderer messageRenderer() {
        return new StandardOutputMessageRenderer();
    }

    @Bean
    public MessageProvider messageProvider() {
        return new HelloWorldMessageProvider();
    }

    @Bean
    public MessageProvider messageProviderConf() {
        return new ConfigurableMessageProvider("Message");
    }


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
        sb.setAge(32);
        return sb;
    }
}
