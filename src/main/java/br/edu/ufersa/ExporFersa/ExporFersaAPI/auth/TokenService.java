package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class TokenService {
    
    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(Auth auth) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                    .withIssuer("ExporFersaAPI")
                    .withSubject(auth.getEmail())
                    .withClaim("role", auth.getRole().name())
                    .withExpiresAt(Instant.now().plus(15, ChronoUnit.MINUTES))
                    .sign(algorithm);
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
            .withIssuer("ExporFersaAPI")
            .build()
            .verify(token)
            .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}
