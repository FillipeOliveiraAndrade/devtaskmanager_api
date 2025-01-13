package br.com.fillipeoliveira.devtask_manager_api.modules.Comment.dtos;

import java.util.UUID;

import lombok.Builder;

/**
 * DTO que representa o autor de um comentário.
 * Contém informações essenciais como o ID e o nome do autor.
 */
@Builder
public record AuthorDTO(
  UUID id,
  String name
) {}
