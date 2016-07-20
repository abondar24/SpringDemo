package org.abondar.experimental.springrest;

import org.abondar.experimental.springdatabase.jpa.JPAConfig;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by abondar on 19.07.16.
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.abondar.experimental.springrest")
@Import(JPAConfig.class)
public class RestConfig extends WebMvcConfigurerAdapter{

}
