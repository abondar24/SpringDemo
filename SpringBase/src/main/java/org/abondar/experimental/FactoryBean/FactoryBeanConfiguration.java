package org.abondar.experimental.FactoryBean;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.security.MessageDigest;


/**
 * Created by abondar on 03.07.16.
 */

@Configuration
public class FactoryBeanConfiguration {


    @Bean
    MessageDigestFactoryBean messageDigestFactoryBean() throws Exception{
        return new MessageDigestFactoryBean();
    }

    @Bean
    MessageDigestFactoryBean messageSHADigestFactoryBean() throws Exception{
        return new MessageDigestFactoryBean();
    }

    @Bean
    MessageDigest shaDigest() throws Exception{
        MessageDigestFactoryBean factoryBean = messageDigestFactoryBean();
         factoryBean.setAlgorithmName("SHA1");
        return factoryBean.getObject();
    }


    @Bean
    MessageDigest defaultDigest() throws Exception {
        return messageDigestFactoryBean().getObject();
    }

        @Bean
       MessageDigester digester() throws Exception{
            return new MessageDigester();
    }

}
