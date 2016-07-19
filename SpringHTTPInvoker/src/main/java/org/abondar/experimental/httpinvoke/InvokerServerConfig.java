package org.abondar.experimental.httpinvoke;

import org.abondar.experimental.springdatabase.jdbc.ContactDao;
import org.abondar.experimental.springdatabase.jdbc.JdbcConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by abondar on 19.07.16.
 */
@Configuration
@EnableWebMvc
@Import(JdbcConfiguration.class)
@ComponentScan("org.abondar.experimental")
public class InvokerServerConfig {

    @Bean
    HttpInvokerServiceExporter conctactExporter() {
        HttpInvokerServiceExporter serviceExporter = new HttpInvokerServiceExporter();
        //how set service?
        serviceExporter.setService(JdbcConfiguration.contactDao());
        serviceExporter.setServiceInterface(ContactDao.class);
 return serviceExporter;
    }

}