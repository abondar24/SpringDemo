package org.abondar.experimental.springboot.auth.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DemoUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("vasya")) {
            throw  new UsernameNotFoundException("Unknown user");
        }
        //encryption must be done on user save to db
        return DemoUserPrincipal.create(username,passwordEncoder.encode("password"));
    }
}
