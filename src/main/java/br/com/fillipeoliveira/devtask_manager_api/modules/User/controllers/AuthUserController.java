package br.com.fillipeoliveira.devtask_manager_api.modules.User.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.AuthUserDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.AuthUserResponseDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.services.AuthUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/auth/users")
public class AuthUserController {

  @Autowired
  private AuthUserService authUserService;

  /**
   * Autentica um usuário com base nas credenciais fornecidas.
   *
   * @param authUserDTO Objeto contendo o e-mail e senha do usuário.
   * @return Token JWT gerado e informações do usuário autenticado.
   */
  @PostMapping
  @Operation(summary = "Autenticar usuário", description = "Autentica um usuário com base no e-mail e senha fornecidos, retornando um token JWT.", responses = {
      @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso.", content = @Content(schema = @Schema(implementation = AuthUserResponseDTO.class))),
      @ApiResponse(responseCode = "401", description = "Credenciais inválidas ou não autorizadas.", content = @Content(schema = @Schema(example = "{ \"error\": \"Unauthorized\" }")))
  })
  public ResponseEntity<AuthUserResponseDTO> login(
      @RequestBody AuthUserDTO authUserDTO
  ) {
    AuthUserResponseDTO result = this.authUserService.auth(authUserDTO);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}
