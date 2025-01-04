package br.com.fillipeoliveira.devtask_manager_api.modules.Project.exceptions;

public class ProjectNotFoundException extends RuntimeException {
  public ProjectNotFoundException() {
    super("Project not found");
  }
}
