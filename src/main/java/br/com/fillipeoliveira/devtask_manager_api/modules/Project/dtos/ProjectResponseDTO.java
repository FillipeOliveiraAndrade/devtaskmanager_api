package br.com.fillipeoliveira.devtask_manager_api.modules.Project.dtos;

import java.time.LocalDate;
import java.util.UUID;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;
import lombok.Builder;

@Builder
public record ProjectResponseDTO(
  UUID id,
  String name,
  String description,
  LocalDate dueDate,
  UUID adminId
) {
  
  public static ProjectResponseDTO fromEntity(Project project) {
    return ProjectResponseDTO.builder()
        .id(project.getId())
        .name(project.getName())
        .description(project.getDescription())
        .dueDate(project.getDueDate())
        .adminId(project.getAdmin().getId())
        .build();
  }
}
