package br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos;

/**
 * DTO de solicitação de autenticação de usuário.
 * 
 * Contém o e-mail e a senha do usuário para realizar o processo de login.
 */
public record AuthUserDTO(
  String email,
  String password
) {}
