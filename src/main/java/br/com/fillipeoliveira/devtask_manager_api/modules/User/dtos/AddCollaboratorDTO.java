package br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos;

import java.util.UUID;

/**
 * DTO para adicionar um colaborador a um projeto.
 * 
 * Contém o e-mail do usuário que será adicionado como colaborador e o ID do projeto.
 */
public record AddCollaboratorDTO(
  String userEmail,
  UUID projectId
) {}
