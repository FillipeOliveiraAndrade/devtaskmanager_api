package br.com.fillipeoliveira.devtask_manager_api.modules.Project.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.dtos.TaskResponseDTO;
import lombok.Builder;

/**
 * DTO utilizado para representar a resposta de um projeto.
 * Este DTO contém as informações detalhadas de um projeto, incluindo
 * seu nome, descrição, data de conclusão, admin responsável, tarefas associadas e membros do projeto.
 * 
 * @param id        O identificador único do projeto.
 * @param name      O nome do projeto.
 * @param description Descrição do projeto.
 * @param dueDate   Data de conclusão do projeto.
 * @param adminId   Identificador do administrador do projeto.
 * @param tasks     Lista de tarefas associadas ao projeto.
 * @param members   Lista de membros (colaboradores) do projeto.
 */
@Builder
public record ProjectResponseDTO(
    UUID id,                 
    String name,
    String description,
    LocalDate dueDate, 
    UUID adminId,
    List<TaskResponseDTO> tasks,
    List<MembersDTO> members
) {

  /**
   * Converte uma entidade `Project` para um DTO `ProjectResponseDTO`.
   * 
   * @param project A entidade `Project` a ser convertida.
   * @return O DTO `ProjectResponseDTO` contendo os dados do projeto.
   */
  public static ProjectResponseDTO fromEntity(Project project) {
    List<TaskResponseDTO> tasksDTO = project.getTasks().stream()
        .map(TaskResponseDTO::fromEntity)
        .collect(Collectors.toList());

    List<MembersDTO> membersDTO = project.getCollaborators().stream()
        .map(collaborator -> MembersDTO.builder()
            .id(collaborator.getId())
            .name(collaborator.getName())
            .email(collaborator.getEmail())
            .build())
        .collect(Collectors.toList());

    return ProjectResponseDTO.builder()
        .id(project.getId()) 
        .name(project.getName())
        .description(project.getDescription())
        .dueDate(project.getDueDate()) 
        .adminId(project.getAdmin().getId()) 
        .tasks(tasksDTO)
        .members(membersDTO)
        .build();
  }
}
