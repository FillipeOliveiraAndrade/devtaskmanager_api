package br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos;

import lombok.Builder;

/**
 * DTO de resposta para autenticação de usuário.
 * 
 * Contém as informações do usuário autenticado e o token gerado após a autenticação.
 */
@Builder
public record AuthUserResponseDTO(
  UserResponseDTO user,
  String token
) {}
