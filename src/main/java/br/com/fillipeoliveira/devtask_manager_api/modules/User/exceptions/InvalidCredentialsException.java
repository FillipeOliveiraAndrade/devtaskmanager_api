package br.com.fillipeoliveira.devtask_manager_api.modules.User.exceptions;

public class InvalidCredentialsException extends RuntimeException {
  public InvalidCredentialsException() {
    super("Invalid credentials");
  }
}
