package org.abondar.experimental.springrest;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by abondar on 19.07.16.
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.abondar.experimental.springrest")
public class RestConfig extends WebMvcConfigurerAdapter{

}
