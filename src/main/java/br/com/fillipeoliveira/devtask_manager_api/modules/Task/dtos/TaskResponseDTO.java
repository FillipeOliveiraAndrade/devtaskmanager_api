package br.com.fillipeoliveira.devtask_manager_api.modules.Task.dtos;

import java.util.UUID;

import br.com.fillipeoliveira.devtask_manager_api.modules.Task.enums.Status;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.entities.Task;
import lombok.Builder;

@Builder
public record TaskResponseDTO(
  String title,
  String description,
  Status status,
  UUID projectId
) {
  
  public static TaskResponseDTO fromEntity(Task task) {
    return TaskResponseDTO.builder()
        .title(task.getTitle())
        .description(task.getDescription())
        .status(task.getStatus())
        .projectId(task.getProject().getId())
        .build();
  }
}
