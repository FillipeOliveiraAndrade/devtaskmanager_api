package br.com.fillipeoliveira.devtask_manager_api.modules.Project.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.dtos.ProjectResponseDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.services.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
  
  @Autowired
  private ProjectService projectService;

  @GetMapping("/{projectId}")
  public ResponseEntity<ProjectResponseDTO> findById(@PathVariable UUID projectId) {
    Project project = this.projectService.findById(projectId);
    return ResponseEntity.status(HttpStatus.OK).body(ProjectResponseDTO.fromEntity(project));
  }
  
  @DeleteMapping("/{projectId}")
  public ResponseEntity<Void> deleteById(@PathVariable UUID projectId) {
    this.projectService.delete(projectId);
    return ResponseEntity.noContent().build();
  }

}
