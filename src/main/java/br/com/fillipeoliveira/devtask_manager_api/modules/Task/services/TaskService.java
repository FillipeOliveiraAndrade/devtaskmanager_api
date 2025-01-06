package br.com.fillipeoliveira.devtask_manager_api.modules.Task.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.exceptions.ProjectNotFoundException;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.repositories.ProjectRepository;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.exceptions.TaskAlreadyExistsException;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.entities.Task;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.repositories.TaskRepository;

@Service
public class TaskService {
  
  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private ProjectRepository projectRepository;

  public Task save(UUID projectId, Task task) {
    Project project = this.projectRepository.findById(projectId).orElseThrow(
      () -> new ProjectNotFoundException()
    );
    
    this.taskRepository.findByTitleAndProjectId(
        task.getTitle(), projectId
    ).ifPresent(existingTask -> {
        throw new TaskAlreadyExistsException();
    });

    task.setProject(project);
  
    return this.taskRepository.save(task);
  }
}
