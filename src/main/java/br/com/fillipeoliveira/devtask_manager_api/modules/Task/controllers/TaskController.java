package br.com.fillipeoliveira.devtask_manager_api.modules.Task.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fillipeoliveira.devtask_manager_api.modules.Comment.dtos.*;
import br.com.fillipeoliveira.devtask_manager_api.modules.Comment.models.entities.Comment;
import br.com.fillipeoliveira.devtask_manager_api.modules.Comment.services.CommentService;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.dtos.*;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.entities.Task;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @Autowired
  private CommentService commentService;

  /**
   * Atualiza o status de uma tarefa.
   */
  @PutMapping("/{taskId}")
  @Operation(summary = "Atualizar o status da tarefa", description = "Atualiza o status de uma tarefa pelo ID.")
  public ResponseEntity<TaskResponseDTO> updateTaskStatus(
      @Parameter(description = "ID da tarefa", required = true) @PathVariable UUID taskId,
      @RequestBody UpdateTaskDTO updateTaskDTO
  ) {

    Task task = this.taskService.updateStatus(taskId, updateTaskDTO.status());
    return ResponseEntity.status(HttpStatus.OK).body(TaskResponseDTO.fromEntity(task));
  }

  /**
   * Adiciona um comentário a uma tarefa.
   */
  @PostMapping("/{taskId}/comments")
  @Operation(summary = "Adicionar comentário", description = "Adiciona um comentário a uma tarefa pelo ID.")
  public ResponseEntity<CommentResponseDTO> createComment(
      @Parameter(description = "ID da tarefa", required = true) @PathVariable UUID taskId,
      @RequestBody CreateCommentDTO createCommentDTO,
      HttpServletRequest request
  ) {

    var userId = UUID.fromString(request.getAttribute("user_id").toString());
    Comment comment = this.commentService.save(taskId, userId, createCommentDTO.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(CommentResponseDTO.fromEntity(comment));
  }
}
