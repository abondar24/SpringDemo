package org.abondar.experimental.httpinvoke.client;

import org.abondar.experimental.springdata.jdbc.ContactDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * Created by abondar on 19.07.16.
 */
@Configuration
public class InvokerClientConfig {

    @Bean
    HttpInvokerProxyFactoryBean remoteContactService(){
        HttpInvokerProxyFactoryBean remoteContactService = new HttpInvokerProxyFactoryBean();
        //need to set the right link
        remoteContactService.setServiceUrl("http://localhost:8080/SpringHTTPInvoker/remoting/ContactService");
        remoteContactService.setServiceInterface(ContactDao.class);
        return remoteContactService;
    }
}
