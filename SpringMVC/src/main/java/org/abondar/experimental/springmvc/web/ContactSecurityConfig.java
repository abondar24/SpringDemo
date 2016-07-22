package org.abondar.experimental.springmvc.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by abondar on 22.07.16.
 */
@Configuration
@EnableWebSecurity
public class ContactSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/*").permitAll()
                .and()
                .formLogin()
                .loginPage("/contacts")
                .failureForwardUrl("/security/loginfail")
                .successForwardUrl("/contacts")
                .defaultSuccessUrl("/contacts");

    }
}
