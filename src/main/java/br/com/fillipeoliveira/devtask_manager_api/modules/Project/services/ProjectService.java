package br.com.fillipeoliveira.devtask_manager_api.modules.Project.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
