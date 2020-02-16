package org.abondar.experimental.springboot.auth.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class DemoUserPrincipal implements UserDetails {

    private String userName;
    private String passowrd;
    private Collection<? extends GrantedAuthority> authorities;

    public DemoUserPrincipal(String userName, String passowrd, Collection<? extends GrantedAuthority> authorities) {
        this.userName = userName;
        this.passowrd = passowrd;
        this.authorities = authorities;
    }

    public static DemoUserPrincipal create(String userName, String passowrd){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("admin"));

        return new DemoUserPrincipal(userName,passowrd,authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passowrd;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemoUserPrincipal that = (DemoUserPrincipal) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(passowrd, that.passowrd) &&
                Objects.equals(authorities, that.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, passowrd, authorities);
    }
}
