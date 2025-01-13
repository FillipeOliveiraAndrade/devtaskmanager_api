package br.com.fillipeoliveira.devtask_manager_api.modules.Project.exceptions;

public class ProjectAlreadyExistsException extends RuntimeException {
  public ProjectAlreadyExistsException() {
    super("This project already exists");
  }
}
