package br.com.fillipeoliveira.devtask_manager_api.modules.User.controllers;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.CreateUserDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.UpdateUserDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.dtos.UserResponseDTO;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  @Operation(summary = "Criar um usuário", description = "Cria um novo usuário com os dados fornecidos.")
  public ResponseEntity<UserResponseDTO> create(
      @RequestBody CreateUserDTO createUserDTO
  ) {
    User user = this.userService.save(createUserDTO.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseDTO.fromEntity(user));
  }

  @GetMapping("/{userId}")
  @Operation(summary = "Obter usuário por ID", description = "Retorna as informações de um usuário com base no ID.")
  public ResponseEntity<UserResponseDTO> getUserById(
      @Parameter(description = "ID do usuário", required = true) @PathVariable UUID userId
  ) {
    User result = this.userService.findById(userId);
    return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.fromEntity(result));
  }

  @PutMapping("/{userId}")
  @Operation(summary = "Atualizar usuário", description = "Atualiza as informações de um usuário.")
  public ResponseEntity<UserResponseDTO> update(
      @Parameter(description = "ID do usuário", required = true) @PathVariable UUID userId,
      @RequestBody UpdateUserDTO userDTO
  ) {
    User updatedUser = this.userService.update(userId, userDTO.toEntity());
    return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.fromEntity(updatedUser));
  }

  @PutMapping("/{userId}/avatar")
  @Operation(summary = "Atualizar avatar do usuário", description = "Atualiza o avatar de um usuário pelo ID.")
  public ResponseEntity<UserResponseDTO> updateAvatar(
      @Parameter(description = "ID do usuário", required = true) @PathVariable UUID userId,
      @RequestPart("avatar") MultipartFile avatar
  ) throws IOException {
    User result = this.userService.updateAvatar(userId, avatar);
    return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.fromEntity(result));
  }

  @GetMapping("/image/{userId}")
  @Operation(summary = "Visualizar avatar do usuário", description = "Retorna o avatar do usuário.")
  public ResponseEntity<byte[]> viewImages(
      @Parameter(description = "ID do usuário", required = true) @PathVariable UUID userId) {
    byte[] image = this.userService.getAvatar(userId);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, "image/png")
        .body(image);
  }
}
