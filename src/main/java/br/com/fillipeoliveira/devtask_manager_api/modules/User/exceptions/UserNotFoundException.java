package br.com.fillipeoliveira.devtask_manager_api.modules.User.exceptions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    super("User not found");
  }
  
}
