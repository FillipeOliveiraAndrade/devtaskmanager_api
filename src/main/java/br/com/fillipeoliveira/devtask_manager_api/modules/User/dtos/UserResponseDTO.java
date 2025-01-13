package br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos;

import java.util.UUID;
import java.util.Optional;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.dtos.ProjectResponseDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.enums.Role;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;
import lombok.Builder;

@Builder
public record UserResponseDTO(
  UUID id,
  String name,
  String email,
  Role role,
  ProjectResponseDTO assignedProject
) {

  /**
   * Converte uma entidade User para o DTO UserResponseDTO.
   *
   * @param user a entidade User a ser convertida
   * @return o DTO UserResponseDTO correspondente
   */
  public static UserResponseDTO fromEntity(User user) {
    ProjectResponseDTO projectDTO = Optional.ofNullable(user.getAssignedProject())
        .map(ProjectResponseDTO::fromEntity)
        .orElse(null);

    return UserResponseDTO.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .role(user.getRole())
        .assignedProject(projectDTO)
        .build();
  }
}
