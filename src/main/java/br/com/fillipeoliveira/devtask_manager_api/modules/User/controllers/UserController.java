package br.com.fillipeoliveira.devtask_manager_api.modules.User.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.CreateUserDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
  
  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<User> create(@RequestBody CreateUserDTO createUserDTO) {
    User result = this.userService.save(createUserDTO.toEntity());
    return ResponseEntity.ok(result);
  } 
}
