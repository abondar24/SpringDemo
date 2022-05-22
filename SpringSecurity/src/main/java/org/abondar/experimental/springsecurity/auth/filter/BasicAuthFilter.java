package org.abondar.experimental.springsecurity.auth.filter;

import org.abondar.experimental.springsecurity.exception.PasswordException;
import org.abondar.experimental.springsecurity.service.JwtService;
import org.abondar.experimental.springsecurity.service.UserService;
import org.abondar.experimental.springsecurity.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class BasicAuthFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(BasicAuthFilter.class);


    private UserService userService;

    private JwtService jwtService;

    @Autowired
    public BasicAuthFilter(AuthenticationManager authenticationManager, UserService userService, JwtService jwtService) {
        super(authenticationManager);
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var path = request.getContextPath();
        if (path.contains("/login")) {
            var authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Basic: ")) {
                var token = authHeader.split("Basic ")[1];
                var credentials = parseAuthToken(token);

                var userData = userService.findByUsername(credentials[0]);
                if (userData.isEmpty()) {
                    logger.error("User not found");
                }

                var pwdHash = userData.get().hash();
                if (!PasswordUtil.verifyPassword(credentials[1], pwdHash)) {
                    logger.error("Wrong password");
                    throw new PasswordException("Wrong password");
                }


                Authentication auth = new UsernamePasswordAuthenticationToken(credentials[0], credentials[1], null);

                auth = getAuthenticationManager().authenticate(auth);
                SecurityContextHolder.getContext().setAuthentication(auth);
                getAuthenticationManager().authenticate(auth);

                var jwt = jwtService.generateToken(userData.get());
                response.setHeader("Authorization", "Bearer: " + jwt);
            } else {
                logger.error("Missing auth");
            }

        }

        filterChain.doFilter(request, response);
    }

    private String[] parseAuthToken(String token) {
        byte[] credDecoded = Base64.getDecoder().decode(token);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        return credentials.split(":", 2);
    }


}
