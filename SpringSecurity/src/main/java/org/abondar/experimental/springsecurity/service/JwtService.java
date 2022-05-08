package org.abondar.experimental.springsecurity.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.issuer}")
    private String jwtIssuer;

    @Value("${jwt.type}")
    private String jwtType;

    @Value("${jwt.audience}")
    private String jwtAudience;

    private UserService userService;

    @Autowired
    public JwtService(UserService userService) {
        this.userService = userService;
    }

    private static final String ROLE_CLAIM = "roles";

    private static final String PWD_CLAIM = "pwd";

    private static final long EXP_TIME = 3600;

    public String generateToken(String username, String password,List<String> roles){

        var secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        var claims = Map.of(ROLE_CLAIM,roles,PWD_CLAIM,password);
        return Jwts.builder()
                .signWith(secretKey)
                .setHeaderParam("typ",jwtType)
                .setAudience(jwtAudience)
                .setSubject(username)
                .setIssuer(jwtIssuer)
                .setExpiration(new Date(System.currentTimeMillis()+EXP_TIME))
                .setClaims(claims)
                .compact();
    }


    public boolean validateToken(String token){
      var parser = getParser();

      var claims = parser
              .parseClaimsJws(token);

      var username = claims.getBody().getSubject();

      var userData= userService.find(username);
      if (userData.isPresent()){
          var pwd = parser.parseClaimsJwt(token).getBody().get(PWD_CLAIM);
          return  pwd.equals(userData.get().hash());
      }

      return false;
    }


    private JwtParser getParser(){
        return Jwts.parserBuilder()
                .requireIssuer(jwtIssuer)
                .requireAudience(jwtAudience)
                .build();
    }

}
