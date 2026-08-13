package mini_music_streaming.music_streaming.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil 
{
        private String SECRET ="mySecretKeyForJwtAuthentication123456";

        private Key getKey()
        {
                return Keys.hmacShaKeyFor(
            SECRET.getBytes());
        }

        public String generateToken(String email, String role)
        {
                return Jwts.builder()

                .subject(email)
                .claim("role", role)

                .issuedAt(
                        new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                + 86400000))

                .signWith(getKey())

                .compact();
        }

        public String extractEmail(String token)
        {
                return Jwts.parser()

            .verifyWith((javax.crypto.SecretKey) getKey())

            .build()

            .parseSignedClaims(token)

            .getPayload()

            .getSubject();
        }

        public Date extractExpiration(
        String token)
{
    return Jwts.parser()

        .verifyWith(
            (javax.crypto.SecretKey)getKey())

        .build()

        .parseSignedClaims(token)

        .getPayload()

        .getExpiration();
}

        public boolean isTokenExpired(
                String token)
        {
                return extractExpiration(token)
                .before(new Date());
        }

        public boolean validateToken(
                String token,
                String email)
        {
                return extractEmail(token)
            .equals(email)

            &&

            !isTokenExpired(token);
        }


}

