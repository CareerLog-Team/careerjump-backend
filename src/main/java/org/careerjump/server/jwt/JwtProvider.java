package org.careerjump.server.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;


    public String create(String userId) {
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setHeaderParam("typ", "JWT")
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .compact();
    }

    public String validateToken(String jwt) {
        String subject = null;
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        log.debug("target Jwt : {}", jwt);

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            subject = claims.getSubject();

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        log.debug("result subject : {}", subject);
        return subject;
    }

}
