package br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos;

import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;

public record UpdateUserDTO(
  String name,
  String email
) {
  
  /**
   * Converte o DTO para a entidade User.
   * 
   * @return A entidade User com os dados do DTO
   * @throws IllegalArgumentException Se os dados do DTO forem inválidos
   */
  public User toEntity() {
    return User.builder()
        .name(this.name())
        .email(this.email())
        .build();
  }
}
