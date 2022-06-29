package org.abondar.experimental.springsecurity.auth.filter;

import org.abondar.experimental.springsecurity.exception.PasswordException;
import org.abondar.experimental.springsecurity.service.JwtService;
import org.abondar.experimental.springsecurity.service.UserService;
import org.abondar.experimental.springsecurity.util.HeaderUtil;
import org.abondar.experimental.springsecurity.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class BasicAuthFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(BasicAuthFilter.class);


    private UserService userService;

    private JwtService jwtService;

    @Autowired
    public BasicAuthFilter(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var authHeader = request.getHeader(HeaderUtil.AUTH_HEADER);

        if (authHeader != null && authHeader.startsWith(HeaderUtil.BASIC_PREFIX)) {
            var token = authHeader.split(HeaderUtil.BASIC_PREFIX)[1];
            var credentials = parseAuthToken(token);
            var userData = userService.findByUsername(credentials[0]);
            if (userData.isEmpty()) {
                logger.error("User not found");
            } else {
                var pwdHash = userData.get().hash();
                if (!PasswordUtil.verifyPassword(credentials[1], pwdHash)) {
                    logger.error("Wrong password");
                    throw new PasswordException("Wrong password");
                }

                Authentication auth = new UsernamePasswordAuthenticationToken(credentials[0], credentials[1], null);

                SecurityContextHolder.getContext().setAuthentication(auth);

                var jwt = jwtService.generateToken(userData.get());
                response.setHeader(HeaderUtil.AUTH_HEADER, HeaderUtil.BEARER_PREFIX + jwt);
            }

        } else {
            logger.error("Missing auth");
        }

        filterChain.doFilter(request, response);
    }

    private String[] parseAuthToken(String token) {
        byte[] credDecoded = Base64.getDecoder().decode(token);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        return credentials.split(":", 2);
    }


}
