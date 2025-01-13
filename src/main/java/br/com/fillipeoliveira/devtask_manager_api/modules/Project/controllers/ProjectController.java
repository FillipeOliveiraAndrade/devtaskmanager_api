package br.com.fillipeoliveira.devtask_manager_api.modules.Project.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.dtos.*;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.services.ProjectService;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.dtos.*;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.entities.Task;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/projects")
public class ProjectController {

  @Autowired
  private ProjectService projectService;

  @Autowired
  private TaskService taskService;

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  @Operation(summary = "Criar um projeto", description = "Cria um novo projeto associado a um usuário.")
  public ResponseEntity<ProjectResponseDTO> createProject(
      @RequestBody CreateProjectDTO createProjectDTO,
      HttpServletRequest request
  ) {

    var userId = request.getAttribute("user_id");
    Project result = this.projectService.save(createProjectDTO.toEntity(), UUID.fromString(userId.toString()));
    return ResponseEntity.status(HttpStatus.CREATED).body(ProjectResponseDTO.fromEntity(result));
  }

  @GetMapping("/{projectId}")
  @Operation(summary = "Obter detalhes do projeto", description = "Retorna os detalhes completos de um projeto pelo ID.")
  public ResponseEntity<ProjectResponseDTO> findById(
      @Parameter(description = "ID do projeto", required = true) @PathVariable UUID projectId
  ) {

    Project project = this.projectService.findById(projectId);
    return ResponseEntity.status(HttpStatus.OK).body(ProjectResponseDTO.fromEntity(project));
  }

  @PostMapping("/{projectId}/tasks")
  @Operation(summary = "Criar uma tarefa", description = "Cria uma nova tarefa associada a um projeto.")
  public ResponseEntity<TaskResponseDTO> createTask(
      @Parameter(description = "ID do projeto", required = true) @PathVariable UUID projectId,
      @RequestBody CreateTaskDTO createTaskDTO
  ) {

    Task task = this.taskService.save(projectId, createTaskDTO.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(TaskResponseDTO.fromEntity(task));
  }

  @DeleteMapping("/{taskId}/tasks")
  @Operation(summary = "Excluir uma tarefa", description = "Exclui uma tarefa com base no ID.")
  public ResponseEntity<Void> deleteTaskById(
      @Parameter(description = "ID da tarefa", required = true) @PathVariable UUID taskId    
  ) {

    this.taskService.delete(taskId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{projectId}")
  @PreAuthorize("hasRole('ADMIN')")
  @Operation(summary = "Excluir um projeto", description = "Exclui um projeto pelo ID fornecido.")
  public ResponseEntity<Void> deleteProjectById(
      @Parameter(description = "ID do projeto", required = true) @PathVariable UUID projectId
  ) {

    this.projectService.delete(projectId);
    return ResponseEntity.noContent().build();
  }
}
