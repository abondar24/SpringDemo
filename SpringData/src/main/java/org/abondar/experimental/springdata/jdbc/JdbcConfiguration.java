package org.abondar.experimental.springdata.jdbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by abondar on 07.07.16.
 */
@Configuration
@PropertySource("classpath:db.properties")
public class JdbcConfiguration {

    @Value("${jdbc.driverClassName}")
    public String driverClassName;

    @Value("${jdbc.url}")
    public String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public static ContactDao contactDao(){
        return new ContactDAOImpl();
    }



}
