package br.com.fillipeoliveira.devtask_manager_api.modules.Task.dtos;

import br.com.fillipeoliveira.devtask_manager_api.modules.Task.enums.Status;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.entities.Task;

public record CreateTaskDTO(
  String title,
  String description,
  Status status
) {
  
  public Task toEntity() {
    return Task.builder()
        .title(this.title())
        .description(this.description())
        .status(this.status())
        .build();
  }
}
