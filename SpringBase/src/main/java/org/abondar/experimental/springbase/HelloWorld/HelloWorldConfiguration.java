package org.abondar.experimental.springbase.HelloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;


/**
 * Created by abondar on 03.07.16.
 */

@PropertySource(value = "classpath:helloWorld.properties")
@ComponentScan(basePackages = {"org.abondar.experimental"})
@Configuration
public class HelloWorldConfiguration {

    @Autowired
    Environment env;

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
        System.out.println(env.getProperty("msg"));
        return new ConfigurableMessageProvider(env.getProperty("msg"));
    }

}
