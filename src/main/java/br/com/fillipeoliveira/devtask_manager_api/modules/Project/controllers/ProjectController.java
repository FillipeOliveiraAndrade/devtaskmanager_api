package br.com.fillipeoliveira.devtask_manager_api.modules.Project.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.dtos.ProjectResponseDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.services.ProjectService;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.dtos.CreateTaskDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.dtos.TaskResponseDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.entities.Task;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.services.TaskService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
  
  @Autowired
  private ProjectService projectService;

  @Autowired
  private TaskService taskService;

  @PostMapping("/{projectId}/tasks")
  public ResponseEntity<TaskResponseDTO> createTask(
      @PathVariable UUID projectId,
      @RequestBody CreateTaskDTO createTaskDTO
  ) {

    Task task = this.taskService.save(projectId, createTaskDTO.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(TaskResponseDTO.fromEntity(task));
  }
  
  @GetMapping("/{projectId}")
  public ResponseEntity<ProjectResponseDTO> findById(@PathVariable UUID projectId) {
    Project project = this.projectService.findById(projectId);
    return ResponseEntity.status(HttpStatus.OK).body(ProjectResponseDTO.fromEntity(project));
  }

}
