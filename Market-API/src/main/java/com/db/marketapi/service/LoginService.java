package com.db.marketapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.db.marketapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class LoginService {
    private static final int JWT_TOKEN_VALIDITY = 15;
    @Autowired
    UserRepository userRepository;
    public ArrayList<String> tokens = new ArrayList<>();

    public boolean validation(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password).isPresent();
    }

    public String checkLogin(String username, String password) {
        if (validation(username, password)) {
            try {
                Algorithm algorithm = Algorithm.HMAC256(username);
                String token = JWT.create()
                        .withIssuer("auth0")
                        .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 60))
                        .sign(algorithm);
                tokens.add(token);
                return token;
            } catch (JWTCreationException exception) {
                exception.printStackTrace();
            }
        }
        return "";
    }

    public String checkJWT(String token){
        if(tokens.contains(token))
            return JWT.decode(token).getPayload();
        return "";
    }
}
