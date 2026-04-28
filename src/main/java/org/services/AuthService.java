package org.services;

import java.time.Duration;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthService {

    public String login(String username, String password) {

        return Jwt.issuer("srgs-app")
                .upn(username)
                .groups("user")
                .expiresIn(Duration.ofHours(8))
                .sign();
    }
    
}
