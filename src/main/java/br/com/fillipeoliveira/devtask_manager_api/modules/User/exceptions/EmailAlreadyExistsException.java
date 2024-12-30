package br.com.fillipeoliveira.devtask_manager_api.modules.User.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
  public EmailAlreadyExistsException() {
    super("Email already exists.");
  }
}
