package br.com.fillipeoliveira.devtask_manager_api.modules.Project.dtos;

import java.util.UUID;

import lombok.Builder;

/**
 * DTO utilizado para representar um membro de um projeto.
 * Este DTO contém os dados de um colaborador, incluindo ID, nome e e-mail.
 * 
 * @param id    O identificador único do membro no sistema.
 * @param name  O nome completo do membro.
 * @param email O e-mail do membro.
 */
@Builder
public record MembersDTO(
  UUID id,
  String name,
  String email
) {}
