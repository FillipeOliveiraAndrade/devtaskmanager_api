package br.com.fillipeoliveira.devtask_manager_api.modules.User.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;
  
  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      String token = JWT.create()
          .withIssuer("devtask-manager-api")
          .withExpiresAt(Instant.now().plus(Duration.ofHours(1)))
          .withSubject(user.getId().toString())
          .withClaim("roles", Arrays.asList(user.getRole().name()))
          .sign(algorithm);
  
      return token;
    } catch (JWTCreationException e) {
      throw new RuntimeException("Error while creating token");
    }
  }
}
