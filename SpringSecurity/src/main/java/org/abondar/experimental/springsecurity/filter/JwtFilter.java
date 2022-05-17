package org.abondar.experimental.springsecurity.filter;

import org.abondar.experimental.springsecurity.exception.AuthHeaderException;
import org.abondar.experimental.springsecurity.service.JwtService;
import org.abondar.experimental.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         var authHeader = request.getHeader("Authorization");

         String token = null;
         if (authHeader!=null && authHeader.startsWith("Bearer ")){
             token = authHeader.split("Bearer ")[0];
             if (jwtService.parseAndValidateToken(token).isEmpty()){
                throw new SecurityException("Invalid JWT Token");
             }



           //  auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

//             SecurityContextHolder.getContext().setAuthentication(auth);
         } else {
             throw new AuthHeaderException("Invalid auth header");
         }

         filterChain.doFilter(request,response);
    }
}
