package br.com.fillipeoliveira.devtask_manager_api.modules.Task.dtos;

import br.com.fillipeoliveira.devtask_manager_api.modules.Task.enums.Status;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.entities.Task;

/**
 * DTO utilizado para criar uma nova tarefa no sistema.
 * Esse DTO contém os dados necessários para a criação de uma nova tarefa,
 * incluindo título, descrição e status da tarefa.
 * 
 * @param title       O título da tarefa.
 * @param description A descrição detalhada da tarefa.
 * @param status      O status inicial da tarefa, representado pelo enum {@link Status}.
 */
public record CreateTaskDTO(
  String title,
  String description,
  Status status
) {

  /**
   * Converte o DTO para a entidade {@link Task}.
   * Este método é utilizado para mapear os dados do DTO para uma nova instância da entidade Task,
   * que será persistida no banco de dados.
   * 
   * @return Uma nova instância de Task, preenchida com os dados do DTO.
   */
  public Task toEntity() {
    return Task.builder()
        .title(this.title())
        .description(this.description())
        .status(this.status())
        .build();
  }
}
