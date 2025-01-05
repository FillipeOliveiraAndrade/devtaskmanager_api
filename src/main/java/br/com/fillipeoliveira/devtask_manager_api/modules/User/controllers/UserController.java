package br.com.fillipeoliveira.devtask_manager_api.modules.User.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.dtos.CreateProjectDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.dtos.ProjectResponseDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.services.ProjectService;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.AddCollaboratorDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.CreateUserDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.UserResponseDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.services.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {
  
  @Autowired
  private UserService userService;

  @Autowired
  private ProjectService projectService;

  @PostMapping
  public ResponseEntity<UserResponseDTO> create(@RequestBody CreateUserDTO createUserDTO) {
    User result = this.userService.save(createUserDTO.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseDTO.fromEntity(result));
  }

  @PostMapping("/projects")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ProjectResponseDTO> createProduct(
    @RequestBody CreateProjectDTO createProjectDTO,
    HttpServletRequest request 
  ) {
    
    var userId = request.getAttribute("user_id");
    Project result = this.projectService.save(createProjectDTO.toEntity(), UUID.fromString(userId.toString()));
    return ResponseEntity.status(HttpStatus.CREATED).body(ProjectResponseDTO.fromEntity(result));
  }

  @PostMapping("/projects/collaborator")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<UserResponseDTO> addCollaborator(
      @RequestBody AddCollaboratorDTO addCollaboratorDTO
  ) {

    System.out.println(addCollaboratorDTO);

    User user = this.projectService.addCollaborator(
        addCollaboratorDTO.userEmail(),
        addCollaboratorDTO.projectId()
    );

    return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.fromEntity(user));
  }

  @GetMapping("/{userId}/projects")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<ProjectResponseDTO>> findAllByUserId(
    @PathVariable UUID userId
  ) {

    List<Project> projects = this.projectService.findAllByUserId(userId);
    List<ProjectResponseDTO> projectsDTO = projects.stream().map(
      ProjectResponseDTO::fromEntity
    ).toList();

    return ResponseEntity.status(HttpStatus.OK).body(projectsDTO);
  }

  @PutMapping("/{projectId}/projects")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ProjectResponseDTO> update(
      @PathVariable UUID projectId,
      @RequestBody Project project
  ) {
    Project updatedProject = this.projectService.update(projectId, project);
    return ResponseEntity.status(HttpStatus.OK).body(ProjectResponseDTO.fromEntity(updatedProject));
  }

  @DeleteMapping("/{projectId}/projects")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deleteById(@PathVariable UUID projectId) {
    this.projectService.delete(projectId);
    return ResponseEntity.noContent().build();
  }
}
