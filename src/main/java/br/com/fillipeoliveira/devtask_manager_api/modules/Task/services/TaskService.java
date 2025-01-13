package br.com.fillipeoliveira.devtask_manager_api.modules.Task.services;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.exceptions.ProjectNotFoundException;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.repositories.ProjectRepository;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.enums.Status;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.exceptions.TaskAlreadyExistsException;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.exceptions.TaskNotFoundException;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.entities.Task;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.repositories.TaskRepository;

/**
 * Service responsável pela gestão de tarefas dentro de um projeto.
 * 
 * Contém os métodos necessários para:
 * - Criar novas tarefas, associando-as a um projeto específico;
 * - Atualizar o status de tarefas existentes;
 * - Excluir tarefas;
 * - Garantir que não haja tarefas duplicadas no mesmo projeto.
 * 
 * O serviço também gerencia a lista de comentários das tarefas, inicializando-a
 * como uma lista vazia, caso não tenha sido definida no momento da criação da tarefa.
 */
@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private ProjectRepository projectRepository;

  /**
   * Cria uma nova tarefa associada a um projeto específico.
   * 
   * @param projectId ID do projeto ao qual a tarefa será associada.
   * @param task A tarefa a ser criada.
   * @return A tarefa criada, salva no banco de dados.
   * @throws ProjectNotFoundException Caso o projeto associado não seja encontrado.
   * @throws TaskAlreadyExistsException Caso já exista uma tarefa com o mesmo título no projeto.
   */
  public Task save(UUID projectId, Task task) {
    Project project = this.projectRepository.findById(projectId)
        .orElseThrow(ProjectNotFoundException::new);

    this.taskRepository.findByTitleAndProjectId(task.getTitle(), projectId)
        .ifPresent(existingTask -> {
            throw new TaskAlreadyExistsException();
        });

    if (task.getComments() == null) {
        task.setComments(new ArrayList<>());
    }

    task.setProject(project);
    return this.taskRepository.save(task);
  }

  /**
   * Atualiza o status de uma tarefa existente.
   * 
   * @param taskId ID da tarefa a ser atualizada.
   * @param status O novo status a ser atribuído à tarefa.
   * @return A tarefa atualizada.
   * @throws TaskNotFoundException Caso a tarefa com o ID especificado não seja encontrada.
   */
  public Task updateStatus(UUID taskId, Status status) {
    Task task = this.taskRepository.findById(taskId)
        .orElseThrow(TaskNotFoundException::new);

    task.setStatus(status);
    return this.taskRepository.save(task);
  }

  /**
   * Exclui uma tarefa do banco de dados.
   * 
   * @param taskId ID da tarefa a ser excluída.
   * @throws TaskNotFoundException Caso a tarefa com o ID especificado não seja encontrada.
   */
  public void delete(UUID taskId) {
    this.taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
    this.taskRepository.deleteById(taskId);
  }
}
