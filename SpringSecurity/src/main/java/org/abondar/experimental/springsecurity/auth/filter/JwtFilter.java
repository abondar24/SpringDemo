package org.abondar.experimental.springsecurity.auth.filter;

import com.nimbusds.jose.shaded.json.JSONObject;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.abondar.experimental.springsecurity.model.UserData;
import org.abondar.experimental.springsecurity.model.UserOauthRequest;
import org.abondar.experimental.springsecurity.service.JwtService;
import org.abondar.experimental.springsecurity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Autowired
    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");

        String token;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.split("Bearer ")[1];
            try {
                var auth = jwtService.parseAndValidateToken(token);
                if (auth.isEmpty()) {
                    logger.error("Invalid JWT Token");
                }
                SecurityContextHolder.getContext().setAuthentication(auth.get());
            } catch (MalformedJwtException ex) {
                logger.error(ex.getMessage());
            } catch (ExpiredJwtException ex) {
                var path = request.getRequestURI();
                if (path.contains("refresh")) {
                    var refresh = jwtService.generateRefresh(ex.getClaims());
                    var auth = jwtService.parseAndValidateToken(refresh);
                    SecurityContextHolder.getContext().setAuthentication(auth.get());

                    response.setHeader("Authorization", "Bearer " + refresh);
                    response.setStatus(200);
                }  else {
                    response.setStatus(406);
                }

            }
        }  else {
            logger.error("Invalid auth header");
        }

        filterChain.doFilter(request, response);
    }

}
