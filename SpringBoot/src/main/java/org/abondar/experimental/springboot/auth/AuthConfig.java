package org.abondar.experimental.springboot.auth;

import org.abondar.experimental.springboot.auth.details.DemoUserDetailsService;
import org.abondar.experimental.springboot.auth.jwt.JwtAuthFilter;
import org.abondar.experimental.springboot.auth.jwt.JwtEntryPoint;
import org.abondar.experimental.springboot.auth.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class AuthConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Bean
    public JwtAuthFilter jwtAuthFilter(UserDetailsService userDetailsService){
        return new JwtAuthFilter(jwtTokenProvider,userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new DemoUserDetailsService();
    }



    @Override
    public void configure(AuthenticationManagerBuilder authenticationManager) throws Exception {
        authenticationManager.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/secure/login")
                .permitAll()
                .antMatchers("/demo/**")
                .permitAll()
                .antMatchers("/actuator/**")
                .permitAll()
                .antMatchers("/swagger-ui.html/**")
                .permitAll()
                .antMatchers("/swagger-resources/**")
                .permitAll()
                .antMatchers("/webjars/**")
                .permitAll()
                .antMatchers("/v2/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        httpSecurity.addFilterBefore(jwtAuthFilter(userDetailsService()), UsernamePasswordAuthenticationFilter.class);


    }
}
