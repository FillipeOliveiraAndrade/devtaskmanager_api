package br.com.fillipeoliveira.devtask_manager_api.modules.Comment.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fillipeoliveira.devtask_manager_api.modules.Comment.exceptions.CommentAlreadyExistsException;
import br.com.fillipeoliveira.devtask_manager_api.modules.Comment.models.entities.Comment;
import br.com.fillipeoliveira.devtask_manager_api.modules.Comment.models.repositories.CommentRepository;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.exceptions.TaskNotFoundException;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.entities.Task;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.repositories.TaskRepository;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.exceptions.UserNotFoundException;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.repositories.UserRepository;

/**
 * Serviço responsável pela gestão de comentários nas tarefas.
 * Contém os métodos para criar, buscar e validar comentários.
 */
@Service
public class CommentService {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private UserRepository userRepository;

  /**
   * Cria e salva um comentário para uma tarefa específica, associado a um usuário.
   * Verifica se o comentário já existe antes de persistir.
   * 
   * @param taskId    O ID da tarefa relacionada ao comentário.
   * @param userId    O ID do usuário que está criando o comentário.
   * @param comment   O objeto de comentário a ser salvo.
   * @return          O comentário salvo.
   * @throws UserNotFoundException Se o usuário não for encontrado.
   * @throws TaskNotFoundException Se a tarefa não for encontrada.
   * @throws CommentAlreadyExistsException Se o comentário já existir para essa tarefa.
   */
  public Comment save(UUID taskId, UUID userId, Comment comment) {
    User user = findUserById(userId);
    Task task = findTaskById(taskId);

    validateCommentUniqueness(userId, taskId, comment.getContent());

    comment.setUser(user);
    comment.setTask(task);
    return this.commentRepository.save(comment);
  }

  /**
   * Busca o usuário pelo ID. Lança uma exceção se o usuário não for encontrado.
   * 
   * @param userId O ID do usuário a ser buscado.
   * @return       O usuário encontrado.
   * @throws UserNotFoundException Se o usuário não for encontrado.
   */
  private User findUserById(UUID userId) {
    return this.userRepository.findById(userId)
        .orElseThrow(UserNotFoundException::new);
  }

  /**
   * Busca a tarefa pelo ID. Lança uma exceção se a tarefa não for encontrada.
   * 
   * @param taskId O ID da tarefa a ser buscada.
   * @return       A tarefa encontrada.
   * @throws TaskNotFoundException Se a tarefa não for encontrada.
   */
  private Task findTaskById(UUID taskId) {
    return this.taskRepository.findById(taskId)
        .orElseThrow(TaskNotFoundException::new);
  }

  /**
   * Valida se o comentário já existe para a tarefa e o usuário fornecidos.
   * Caso o comentário já exista, uma exceção será lançada.
   * 
   * @param userId    O ID do usuário que fez o comentário.
   * @param taskId    O ID da tarefa relacionada ao comentário.
   * @param content   O conteúdo do comentário.
   * @throws CommentAlreadyExistsException Se o comentário já existir para essa tarefa.
   */
  private void validateCommentUniqueness(UUID userId, UUID taskId, String content) {
    this.commentRepository.findByUserIdAndTaskIdAndContent(userId, taskId, content)
        .ifPresent(existingComment -> {
          throw new CommentAlreadyExistsException();
        });
  }
}
