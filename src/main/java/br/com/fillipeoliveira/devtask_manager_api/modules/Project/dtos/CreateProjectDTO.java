package br.com.fillipeoliveira.devtask_manager_api.modules.Project.dtos;

import java.time.LocalDate;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;

public record CreateProjectDTO(
  String name,
  String description,
  LocalDate dueDate
) {
  
  public Project toEntity() {
    return Project.builder()
        .name(this.name())
        .description(this.description())
        .dueDate(this.dueDate())
        .build();
  }
}
