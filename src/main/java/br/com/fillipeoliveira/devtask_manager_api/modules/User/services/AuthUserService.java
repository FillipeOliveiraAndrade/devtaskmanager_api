package br.com.fillipeoliveira.devtask_manager_api.modules.User.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.AuthUserDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.exceptions.InvalidCredentialsException;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.repositories.UserRepository;

@Service
public class AuthUserService {
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public void auth(AuthUserDTO authUserDTO) {
    User user = this.userRepository.findByEmail(authUserDTO.email()).orElseThrow(
      () -> new InvalidCredentialsException()
    );

    boolean passwordMatches = passwordEncoder.matches(authUserDTO.password(), user.getPassword());
    if (!passwordMatches) {
      throw new InvalidCredentialsException();
    }
  
    System.out.println("user logado");
  }
}
