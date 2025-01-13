package br.com.fillipeoliveira.devtask_manager_api.modules.User.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.AuthUserDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.AuthUserResponseDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.UserResponseDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.exceptions.InvalidCredentialsException;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.repositories.UserRepository;

@Service
public class AuthUserService {
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthUserResponseDTO auth(AuthUserDTO authUserDTO) {
    User user = this.userRepository.findByEmail(authUserDTO.email()).orElseThrow(
      () -> new InvalidCredentialsException()
    );

    boolean passwordMatches = passwordEncoder.matches(authUserDTO.password(), user.getPassword());
    if (!passwordMatches) {
      throw new InvalidCredentialsException();
    }
  
    String token = this.tokenService.generateToken(user);
    AuthUserResponseDTO tokenDTO = AuthUserResponseDTO.builder()
        .user(UserResponseDTO.fromEntity(user))
        .token(token)
        .build();

    return tokenDTO;
  }
}
