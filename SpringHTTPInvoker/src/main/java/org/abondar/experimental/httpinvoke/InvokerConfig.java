package org.abondar.experimental.httpinvoke;

import org.abondar.experimental.springdatabase.jdbc.ContactDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * Created by abondar on 19.07.16.
 */
@Configuration
public class InvokerConfig {

    @Bean
    HttpInvokerProxyFactoryBean remoteContactService(){
        HttpInvokerProxyFactoryBean remoteContactService = new HttpInvokerProxyFactoryBean();
        //need to set the right link
        remoteContactService.setServiceUrl("http://localhost:8080/httpinvoke/remoting/ContactService");
        remoteContactService.setServiceInterface(ContactDao.class);
        return remoteContactService;
    }
}
