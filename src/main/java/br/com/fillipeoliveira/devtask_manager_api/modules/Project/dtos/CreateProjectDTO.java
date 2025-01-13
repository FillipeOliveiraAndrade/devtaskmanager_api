package br.com.fillipeoliveira.devtask_manager_api.modules.Project.dtos;

import java.time.LocalDate;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;

/**
 * DTO utilizado para criar um novo projeto no sistema.
 * Esse DTO contém os dados necessários para a criação de um projeto,
 * incluindo nome, descrição e data de conclusão prevista.
 * 
 * @param name        O nome do projeto.
 * @param description A descrição detalhada do projeto.
 * @param dueDate     A data de conclusão prevista para o projeto.
 */
public record CreateProjectDTO(
  String name,
  String description,
  LocalDate dueDate
) {

  /**
   * Converte o DTO para a entidade {@link Project}.
   * Este método é utilizado para mapear os dados do DTO para uma nova instância da entidade Project,
   * que será persistida no banco de dados.
   * 
   * @return Uma nova instância de Project, preenchida com os dados do DTO.
   */
  public Project toEntity() {
    return Project.builder()
        .name(this.name())
        .description(this.description())
        .dueDate(this.dueDate()) 
        .build();
  }
}
