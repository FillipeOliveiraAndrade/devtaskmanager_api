package br.com.fillipeoliveira.devtask_manager_api.modules.Comment.models.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fillipeoliveira.devtask_manager_api.modules.Comment.models.entities.Comment;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

  Optional<Comment> findByUserIdAndTaskIdAndContent(
      UUID userId,
      UUID taskId,
      String content
  );
}
