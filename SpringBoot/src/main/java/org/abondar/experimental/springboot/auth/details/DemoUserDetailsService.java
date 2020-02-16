package org.abondar.experimental.springboot.auth.details;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DemoUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("vasya")) {
            throw  new UsernameNotFoundException("Unknown user");
        }
        return DemoUserPrincipal.create(username,"password");
    }
}
