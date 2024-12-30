package br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos;

import br.com.fillipeoliveira.devtask_manager_api.modules.User.enums.Role;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;

public record CreateUserDTO(
  String name,
  String email,
  String password,
  Role role
) {
  
  public User toEntity() {
    return User.builder()
      .name(this.name())
      .email(this.email())
      .password(this.password())
      .role(this.role())
      .build();
  }
}
