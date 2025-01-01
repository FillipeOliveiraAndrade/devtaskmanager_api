package br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
