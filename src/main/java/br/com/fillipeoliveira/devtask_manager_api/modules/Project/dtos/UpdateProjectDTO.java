package br.com.fillipeoliveira.devtask_manager_api.modules.Project.dtos;

import java.time.LocalDate;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;

/**
 * DTO utilizado para representar os dados necessários para atualizar um projeto.
 * Contém as informações que podem ser alteradas em um projeto existente, como o nome,
 * descrição e data de conclusão.
 * 
 * @param name        O nome do projeto.
 * @param description Descrição do projeto.
 * @param dueDate     Data de conclusão do projeto.
 */
public record UpdateProjectDTO(
  String name,
  String description,
  LocalDate dueDate
) {

  /**
   * Converte o DTO `UpdateProjectDTO` para uma entidade `Project`.
   * Este método é utilizado para mapear os dados do DTO para a entidade antes
   * de ser salva ou atualizada no banco de dados.
   * 
   * @return A entidade `Project` com os dados do DTO.
   */
  public Project toEntity() {
    return Project.builder()
        .name(this.name())
        .description(this.description())
        .dueDate(this.dueDate())
        .build();
  }
}
