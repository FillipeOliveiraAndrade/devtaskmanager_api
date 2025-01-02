package br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos;

import java.util.UUID;

import br.com.fillipeoliveira.devtask_manager_api.modules.User.enums.Role;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;
import lombok.Builder;

@Builder
public record UserResponseDTO(
  UUID id,
  String name,
  String email,
  Role role,
  UUID assignedProjectId
) {
  
  public static UserResponseDTO fromEntity(User user) {
    return UserResponseDTO.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .role(user.getRole())
        .assignedProjectId(user.getAssignedProject() != null ? user.getAssignedProject().getId() : null)
        .build();
  }
}
