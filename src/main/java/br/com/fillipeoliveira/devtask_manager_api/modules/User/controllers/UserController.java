package br.com.fillipeoliveira.devtask_manager_api.modules.User.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.dtos.CreateProjectDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.dtos.ProjectResponseDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.services.ProjectService;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.CreateUserDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.UserResponseDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.services.UserService;

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
  public ResponseEntity<ProjectResponseDTO> createProduct(
    @RequestBody CreateProjectDTO createProjectDTO
  ) {
    // TODO: Change to get the logged-in user's id
    Project result = this.projectService.save(createProjectDTO.toEntity(), UUID.fromString("62be4a4f-a5c0-40ee-b29e-24efd9a9fa11"));
    return ResponseEntity.status(HttpStatus.CREATED).body(ProjectResponseDTO.fromEntity(result));
  }
}
