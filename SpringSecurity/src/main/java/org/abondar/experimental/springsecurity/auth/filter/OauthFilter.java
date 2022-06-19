package org.abondar.experimental.springsecurity.auth.filter;

import com.nimbusds.jose.shaded.json.JSONObject;
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
import java.util.List;
import java.util.Map;

@Component
public class OauthFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final JwtDecoder nimbusDecoder;

    @Autowired
    private final  UserService userService;

    @Autowired
    public OauthFilter(JwtService jwtService, JwtDecoder nimbusDecoder, UserService userService) {
        this.jwtService = jwtService;
        this.nimbusDecoder = nimbusDecoder;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var oauthHeader = request.getHeader("X-OAUTH-TOKEN");
            if (oauthHeader != null ){
                var accessToken=nimbusDecoder.decode(oauthHeader);
                var claims = accessToken.getClaims();

                var sub = (String)claims.get("sub");
                var roles = extractRoles(claims);

                var oauthRequest = new UserOauthRequest(sub,roles);
                var userData= userService.addOrUpdateStore(oauthRequest);
                var jwt = jwtService.generateToken(userData);
                var auth = jwtService.parseAndValidateToken(jwt);

                SecurityContextHolder.getContext().setAuthentication(auth.get());

                response.setHeader("Authorization", "Bearer " + jwt);
                response.setStatus(200);

            } else {
                logger.error("No oauth header present");
            }

        filterChain.doFilter(request, response);
    }

    private List<String> extractRoles(Map<String,Object> claims){
        var resourceAccess = (JSONObject) claims.get("resource_access");
        var rolesObj = (JSONObject)resourceAccess.get("spring");

        return  (List<String>) rolesObj.get("roles");
    }
}
