package br.com.fillipeoliveira.devtask_manager_api.modules.Task.exceptions;

public class TaskNotFoundException extends RuntimeException {
  public TaskNotFoundException() {
    super("Task not found");
  }
}
