package org.abondar.experimental.springsecurity.service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.abondar.experimental.springsecurity.model.Claims.AUD_CLAIM;
import static org.abondar.experimental.springsecurity.model.Claims.ISS_CLAIM;
import static org.abondar.experimental.springsecurity.model.Claims.PWD_CLAIM;
import static org.abondar.experimental.springsecurity.model.Claims.ROLE_CLAIM;
import static org.abondar.experimental.springsecurity.model.Claims.SUB_CLAIM;

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

    private final UserService userService;

    @Autowired
    public JwtService(UserService userService) {
        this.userService = userService;
    }



    private static final long EXP_TIME = 36000;

    public String generateToken(String userId){
        var userData = userService.find(userId);
        if (userData.isEmpty()){
            return "";
        }

        var secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        var claims = Map.of(
                ROLE_CLAIM.getVal(),userData.get().roles(),
                PWD_CLAIM.getVal(),userData.get().hash(),
                ISS_CLAIM.getVal(),jwtIssuer,
                AUD_CLAIM.getVal(),jwtAudience,
                SUB_CLAIM.getVal(),userId,
                "exp",new Date(System.currentTimeMillis()+EXP_TIME));

        return Jwts.builder()
                .signWith(secretKey)
                .setHeaderParam("typ",jwtType)
                .setClaims(claims)
                .compact();
    }


    public Optional<Authentication> parseAndValidateToken(String token){
      var parser = getParser();

      var claims = parser
              .parseClaimsJws(token).getBody();

      var userId = claims.getSubject();

      var userData= userService.find(userId);
      if (userData.isPresent()){
          var pwd = claims.get(PWD_CLAIM.getVal());
          if (pwd.equals(userData.get().hash())){
              var roles = (List<String>) claims.get(ROLE_CLAIM.getVal());
              var grantedAuth = roles.stream()
                      .map(SimpleGrantedAuthority::new)
                      .toList();

              var auth = new UsernamePasswordAuthenticationToken(userData.get().login(),null,grantedAuth);
              return Optional.of(auth);
          }
      }

      //TODO: catch ExpiredJwtException
      return Optional.empty();
    }


    private JwtParser getParser(){
        var secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .requireIssuer(jwtIssuer)
                .requireAudience(jwtAudience)
                .build();
    }

}
