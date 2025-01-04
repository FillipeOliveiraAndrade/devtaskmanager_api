package br.com.fillipeoliveira.devtask_manager_api.modules.Project.services;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.exceptions.ProjectNotFoundException;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.repositories.ProjectRepository;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.exceptions.UserNotFoundException;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.repositories.UserRepository;

@Service
public class ProjectService {
  
  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private UserRepository userRepository;

  public Project save(Project project, UUID userId) {
    User user = this.userRepository.findById(userId).orElseThrow(
      () -> new UserNotFoundException()
    );

    project.setAdmin(user);
    return this.projectRepository.save(project);
  }

  public List<Project> findAllByUserId(UUID userId) {
    this.userRepository.findById(userId).orElseThrow(
      () -> new UserNotFoundException()
    );

    return this.projectRepository.findByAdminId(userId);
  } 

  public Project findById(UUID projectId) {
    Project project = this.projectRepository.findById(projectId).orElseThrow(
      () -> new ProjectNotFoundException()
    );

    return project;
  }
  
  public Project update(UUID projectId, Project project) {
    Project existingProject = this.projectRepository.findById(projectId).orElseThrow(
      () -> new ProjectNotFoundException()
    );

    updateFieldIfNotNull(project.getName(), existingProject::setName);
    updateFieldIfNotNull(project.getDescription(), existingProject::setDescription);
    updateFieldIfNotNull(project.getDueDate(), existingProject::setDueDate);

    return this.projectRepository.save(existingProject);
  }   

  public void delete(UUID projectId) {
    this.projectRepository.findById(projectId).orElseThrow(
      () -> new ProjectNotFoundException()
    );

    this.projectRepository.deleteById(projectId);
  }

  private <T> void updateFieldIfNotNull(T newValue, Consumer<T> setter) {
    if (newValue != null) {
      setter.accept(newValue);
    }
  }
}
