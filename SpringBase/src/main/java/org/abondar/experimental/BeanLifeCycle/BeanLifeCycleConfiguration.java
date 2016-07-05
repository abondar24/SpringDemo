package org.abondar.experimental.BeanLifeCycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;


/**
 * Created by abondar on 03.07.16.
 */

@PropertySource(value = "classpath:beanLife.properties")
@Configuration
public class BeanLifeCycleConfiguration {


    @Autowired
    Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public SimpleBean simpleBean1() {
        SimpleBean sb = new SimpleBean();
        sb.setName(env.getProperty("sb1.name"));
        sb.setAge(Integer.valueOf(env.getProperty("sb1.age")));
        return sb;
    }


    @Bean
    public SimpleBean simpleBean2() {
        SimpleBean sb = new SimpleBean();
        sb.setName(env.getProperty("sb2.name"));
        sb.setAge(Integer.valueOf(env.getProperty("sb2.age")));
        return sb;
    }

    @Bean
    public SimpleBean simpleBean3() {
        SimpleBean sb = new SimpleBean();
        sb.setAge(Integer.valueOf(env.getProperty("sb3.age")));
        return sb;
    }

    @Bean

    public SimpleDestuctiveBean simpleDestuctiveBean(){
        return new SimpleDestuctiveBean();
    }
}
