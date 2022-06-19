package org.abondar.experimental.springsecurity.config;

import org.abondar.experimental.springsecurity.auth.AuthEntryPoint;
import org.abondar.experimental.springsecurity.auth.filter.BasicAuthFilter;
import org.abondar.experimental.springsecurity.auth.filter.JwtFilter;
import org.abondar.experimental.springsecurity.auth.filter.OauthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

    @Order(1)
    @Configuration
    public static class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private BasicAuthFilter basicFilter;

        @Autowired
        private AuthEntryPoint entryPoint;


        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {

            httpSecurity
                    .addFilterBefore(basicFilter,BasicAuthenticationFilter.class)
                    .cors()
                    .and()
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/security")
                    .permitAll()
                    .antMatchers(HttpMethod.POST, "/security/login")
                    .authenticated()
                    .and()
                    .httpBasic()
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(entryPoint)
                    .and();

        }
    }

    @Order(2)
    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public static class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private JwtFilter jwtFilter;

        @Autowired
        private AuthEntryPoint entryPoint;

        @Bean
        GrantedAuthorityDefaults grantedAuthorityDefaults() {
            return new GrantedAuthorityDefaults("");
        }

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .cors()
                    .and()
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/security/secret")
                    .hasRole("user")
                    .antMatchers("/security/ultra")
                    .hasRole("admin")
                    .antMatchers(HttpMethod.DELETE,"/security/*")
                    .hasAnyRole("user","admin")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .oauth2ResourceServer()
                    .jwt()
                    .and()
                    .authenticationEntryPoint(entryPoint)
                    .and()
                    .exceptionHandling()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        }


    }
}
