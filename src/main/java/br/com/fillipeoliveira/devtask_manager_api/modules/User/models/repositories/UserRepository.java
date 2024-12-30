package br.com.fillipeoliveira.devtask_manager_api.modules.User.models.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;

public interface UserRepository extends JpaRepository<User, UUID>{
  Optional<User> findByEmail(String email);
}
