package org.abondar.experimental.springboot.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.UserPrincipal;
import java.util.Date;


@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date expiryDate = new Date(new Date().getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(userPrincipal.getName())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.ES512,jwtSecret)
                .compact();
    }

    public String getUserId(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }


    public boolean validteToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(authToken);

            return true;
        } catch (SignatureException ex){
            throw new SignatureException("wrong signature");
        } catch (MalformedJwtException ex) {
            throw new MalformedJwtException("Invalid token");
        } catch (ExpiredJwtException ex){
            System.out.println(ex.getMessage());
        } catch (UnsupportedJwtException ex){
            throw new UnsupportedJwtException("Unsupported token");
        } catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Empty claims");
        }

        return false;
    }
}
