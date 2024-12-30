package br.com.fillipeoliveira.devtask_manager_api.modules.User.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fillipeoliveira.devtask_manager_api.modules.User.exceptions.EmailAlreadyExistsException;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.repositories.UserRepository;

@Service
public class UserService {
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User save(User user) {
    this.userRepository.findByEmail(user.getEmail()).ifPresent(existingUser -> {
      throw new EmailAlreadyExistsException();
    });

    var password = this.passwordEncoder.encode(user.getPassword());
    user.setPassword(password);

    return this.userRepository.save(user);
  }
}
