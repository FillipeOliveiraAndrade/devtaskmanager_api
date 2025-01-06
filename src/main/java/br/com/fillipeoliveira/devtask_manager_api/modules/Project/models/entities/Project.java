package br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.entities.Task;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "project")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;
  private String description;
  private LocalDate dueDate;

  @ManyToOne
  @JoinColumn(name = "admin_id")
  private User admin;

  @OneToMany(mappedBy = "project")
  private List<Task> tasks;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
