package br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos;

import java.util.UUID;

public record AddCollaboratorDTO(
  String userEmail,
  UUID projectId
) {
}
