package org.abondar.experimental.springbase.MessageEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


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
