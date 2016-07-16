package org.abondar.experimental.springbase.FactoryBean;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


import java.security.MessageDigest;


/**
 * Created by abondar on 03.07.16.
 */

@Configuration
@PropertySource(value = "classpath:factoryBean.properties")
public class FactoryBeanConfiguration {

    @Value("${algortithm}")
    private String nonDefAlg;

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
         factoryBean.setAlgorithmName(nonDefAlg);
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
