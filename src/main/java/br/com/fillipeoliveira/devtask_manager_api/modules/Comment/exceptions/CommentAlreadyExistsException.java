package br.com.fillipeoliveira.devtask_manager_api.modules.Comment.exceptions;

public class CommentAlreadyExistsException extends RuntimeException {
  public CommentAlreadyExistsException() {
    super("This comment already exists in the task");
  }
}
