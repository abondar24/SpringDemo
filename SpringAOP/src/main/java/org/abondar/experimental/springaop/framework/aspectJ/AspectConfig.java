package org.abondar.experimental.springaop.framework.aspectJ;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by abondar on 18.07.16.
 */

@EnableAspectJAutoProxy
@Configuration
public class AspectConfig {

    @Bean
    public AspectBean aspectBean() {
        return  new AspectBean();
    }

    @Bean
    public AspectDependency aspectDependency() {
        return new AspectDependency();
    }

    @Bean
    public AspectAdvice aspectAdvice(){
        return new AspectAdvice();
    }


}