package br.com.fillipeoliveira.devtask_manager_api.modules.Project.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.services.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
  
  @Autowired
  private ProjectService projectService;

  @DeleteMapping("/{projectId}")
  public ResponseEntity<Void> deleteById(@PathVariable UUID projectId) {
    this.projectService.delete(projectId);
    return ResponseEntity.noContent().build();
  }
}
