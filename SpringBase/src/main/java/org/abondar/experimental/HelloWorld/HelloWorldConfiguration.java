package org.abondar.experimental.HelloWorld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by abondar on 03.07.16.
 */

@Configuration
public class HelloWorldConfiguration {



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

}
