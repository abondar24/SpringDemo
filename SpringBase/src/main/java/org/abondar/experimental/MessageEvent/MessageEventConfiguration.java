package org.abondar.experimental.MessageEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;


/**
 * Created by abondar on 03.07.16.
 */

@ComponentScan(basePackages = {"org.abondar.experimental"})
@Configuration
public class MessageEventConfiguration {

    @Bean
    Publisher publisher(){
        return new Publisher();
    }

    @Bean
    MessageEventListener messageEventListener(){
        return new MessageEventListener();
    }

}
