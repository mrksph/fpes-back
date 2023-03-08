package com.fpes.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import static com.fpes.utils.Constants.SECRET;

@Component
public class JwtUtils {

    public boolean validateJwtToken(String jwt) {
        // validate jwt token
        DecodedJWT decodedJWT;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET.getBytes()))
                    // specify an specific claim validations
                    .withIssuer("auth0")
                    // reusable verifier instance
                    .build();

            decodedJWT = verifier.verify(jwt);
            return true;
        } catch (JWTVerificationException exception){
            // Invalid signature/claims
        }
        return false;
    }

    public String getUserNameFromJwtToken(String jwt) {
        String subject = JWT.decode(jwt).getSubject();
        return subject;
    }
}
