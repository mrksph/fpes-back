package com.fpes.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.fpes.utils.Constants.SECRET;

@Component
@Slf4j
public class JwtUtils {

    public boolean validateJwtToken(String jwt) {
        // validate jwt token
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC512(SECRET.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            decodedJWT = verifier.verify(jwt);
            return true;
        } catch (AlgorithmMismatchException exception) {
            // Invalid algorithm
            log.error(exception.getMessage());
            log.error("Algorith does not match");
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            log.error(exception.getMessage());
        }

        return false;
    }

    public String getUserNameFromJwtToken(String jwt) {
        String subject = JWT.decode(jwt).getSubject();
        return subject;
    }
}
