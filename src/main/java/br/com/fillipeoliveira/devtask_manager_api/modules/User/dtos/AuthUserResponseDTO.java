package br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos;

import lombok.Builder;

@Builder
public record AuthUserResponseDTO(
  UserResponseDTO user,
  String token
) {
  
}
