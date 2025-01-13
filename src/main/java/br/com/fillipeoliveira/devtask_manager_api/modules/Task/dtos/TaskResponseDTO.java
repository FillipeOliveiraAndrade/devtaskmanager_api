package br.com.fillipeoliveira.devtask_manager_api.modules.Task.dtos;

import java.util.List;
import java.util.UUID;

import br.com.fillipeoliveira.devtask_manager_api.modules.Comment.dtos.CommentResponseDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.enums.Status;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.entities.Task;
import lombok.Builder;

/**
 * DTO (Data Transfer Object) responsável por representar os dados de resposta
 * de uma tarefa. Utilizado para encapsular a resposta de uma tarefa
 * ao ser retornada pela API, contendo informações como título, descrição,
 * status, projeto associado e comentários.
 */
@Builder
public record TaskResponseDTO(
  UUID id,
  String title,
  String description,     
  Status status,
  UUID projectId,
  List<CommentResponseDTO> comments
) {

  /**
   * Converte uma entidade de tarefa para o DTO de resposta.
   * 
   * @param task A tarefa a ser convertida para DTO.
   * @return Um objeto TaskResponseDTO com os dados da tarefa.
   */
  public static TaskResponseDTO fromEntity(Task task) {
    List<CommentResponseDTO> commentsDTO = task.getComments().stream()
        .map(CommentResponseDTO::fromEntity)
        .toList();

    return TaskResponseDTO.builder()
        .id(task.getId())
        .title(task.getTitle())
        .description(task.getDescription())
        .status(task.getStatus())
        .projectId(task.getProject().getId())
        .comments(commentsDTO)
        .build();
  }
}
