package br.com.fillipeoliveira.devtask_manager_api.modules.Comment.dtos;

import br.com.fillipeoliveira.devtask_manager_api.modules.Comment.models.entities.Comment;

/**
 * DTO utilizado para a criação de um comentário.
 * Contém apenas o conteúdo do comentário, que será salvo na base de dados.
 */
public record CreateCommentDTO(
    String content
) {
  
  /**
   * Converte este DTO para uma entidade `Comment`.
   * 
   * @return Uma nova instância da entidade `Comment` com o conteúdo fornecido.
   */
  public Comment toEntity() {
    return Comment.builder()
        .content(this.content())
        .build();
  }
}
