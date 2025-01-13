package br.com.fillipeoliveira.devtask_manager_api.modules.Comment.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.fillipeoliveira.devtask_manager_api.modules.Comment.models.entities.Comment;
import lombok.Builder;

/**
 * DTO de resposta para um comentário.
 * Contém informações sobre o conteúdo do comentário, o autor, a tarefa associada e a data de criação.
 */
@Builder
public record CommentResponseDTO(
    UUID id,            
    String content,
    AuthorDTO author,
    UUID taskId,
    LocalDateTime createdAt 
) {

  /**
   * Método para converter uma entidade `Comment` em um DTO `CommentResponseDTO`.
   * 
   * @param comment A entidade de comentário a ser convertida.
   * @return O DTO `CommentResponseDTO` contendo os dados do comentário.
   */
  public static CommentResponseDTO fromEntity(Comment comment) {
    return CommentResponseDTO.builder()
        .id(comment.getId())
        .content(comment.getContent())
        .taskId(comment.getTask().getId())
        .author(
            AuthorDTO.builder()
                .id(comment.getUser().getId())
                .name(comment.getUser().getName())
                .build())
        .createdAt(comment.getCreatedAt())
        .build();
  }
}
