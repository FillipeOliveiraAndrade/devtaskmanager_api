package br.com.fillipeoliveira.devtask_manager_api.modules.Task.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fillipeoliveira.devtask_manager_api.modules.Task.exceptions.TaskAlreadyExistsException;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.entities.Task;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.repositories.TaskRepository;

@Service
public class TaskService {
  
  @Autowired
  private TaskRepository taskRepository;

  public Task save(Task task) {
    this.taskRepository.findByTitleAndProjectId(
        task.getTitle(), task.getProject().getId()
    ).ifPresent(existingTask -> {
        throw new TaskAlreadyExistsException();
    });
  
    return this.taskRepository.save(task);
  }
}
