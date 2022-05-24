package org.abondar.experimental.springsecurity.service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.abondar.experimental.springsecurity.model.UserData;
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

import static org.abondar.experimental.springsecurity.model.Claims.AUD_CLAIM;
import static org.abondar.experimental.springsecurity.model.Claims.ISS_CLAIM;
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

    @Value("${jwt.expTime}")
    private long expTime;

    private final UserService userService;

    @Autowired
    public JwtService(UserService userService) {
        this.userService = userService;
    }


    public String generateToken(UserData userData) {

        var secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        var claims = Map.of(
                ROLE_CLAIM.getVal(), userData.roles(),
                ISS_CLAIM.getVal(), jwtIssuer,
                AUD_CLAIM.getVal(), jwtAudience,
                SUB_CLAIM.getVal(), userData.login(),
                "exp", new Date(System.currentTimeMillis() + expTime));

        return Jwts.builder()
                .signWith(secretKey)
                .setHeaderParam("typ", jwtType)
                .setClaims(claims)
                .compact();
    }


    public Optional<Authentication> parseAndValidateToken(String token) {
        var parser = getParser();

        var claims = parser
                .parseClaimsJws(token).getBody();

        var login = claims.getSubject();
        var userData = userService.findByUsername(login);
        if (userData.isEmpty()) {
            return Optional.empty();
        }

        var iss = claims.getIssuer();
        if (!iss.equals(jwtIssuer)) {
            return Optional.empty();
        }

        List<String> roles = (List<String>) claims.get(ROLE_CLAIM.getVal());
        var grantedAuth = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        var auth = new UsernamePasswordAuthenticationToken(login, null, grantedAuth);
        return Optional.of(auth);

    }


    private JwtParser getParser() {
        var secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .requireIssuer(jwtIssuer)
                .requireAudience(jwtAudience)
                .build();
    }

}
