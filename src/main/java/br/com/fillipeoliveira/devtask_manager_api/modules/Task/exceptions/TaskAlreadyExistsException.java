package br.com.fillipeoliveira.devtask_manager_api.modules.Task.exceptions;

public class TaskAlreadyExistsException extends RuntimeException {
  public TaskAlreadyExistsException() {
    super("A task with this title already exists in the project.");
  }
}
