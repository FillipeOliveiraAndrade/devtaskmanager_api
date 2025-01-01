package br.com.fillipeoliveira.devtask_manager_api.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.fillipeoliveira.devtask_manager_api.modules.User.exceptions.EmailAlreadyExistsException;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.exceptions.InvalidCredentialsException;

@ControllerAdvice
public class GlobalControllerAdvice {
  
  @ExceptionHandler({
    EmailAlreadyExistsException.class,
    InvalidCredentialsException.class
  })
  public ResponseEntity<ErrorMessageDTO> handleBadRequestException(RuntimeException exception) {
    ErrorMessageDTO error = new ErrorMessageDTO(exception.getMessage(), null);
    return ResponseEntity.badRequest().body(error);
  }
}
