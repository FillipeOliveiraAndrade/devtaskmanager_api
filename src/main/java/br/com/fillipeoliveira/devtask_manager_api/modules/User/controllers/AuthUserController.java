package br.com.fillipeoliveira.devtask_manager_api.modules.User.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.AuthUserDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.services.AuthUserService;

@RestController
@RequestMapping("/auth/users")
public class AuthUserController {

  @Autowired
  private AuthUserService authUserService;
  
  @PostMapping
  public void login(@RequestBody AuthUserDTO authUserDTO) {
    this.authUserService.auth(authUserDTO);
  }
}
