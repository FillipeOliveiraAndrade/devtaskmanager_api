package br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.entities.Task;

public interface TaskRepository extends JpaRepository<Task, UUID> {
  Optional<Task> findByTitleAndProjectId(String title, UUID projectId);
}
