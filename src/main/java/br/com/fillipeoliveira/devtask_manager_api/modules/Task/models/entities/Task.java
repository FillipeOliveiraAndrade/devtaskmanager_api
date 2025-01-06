package br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;
import br.com.fillipeoliveira.devtask_manager_api.modules.Task.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name = "task")
@Data
public class Task {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String title;
  private String description;

  @Enumerated(EnumType.STRING)
  private Status status;
  
  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @CreationTimestamp
  private LocalDateTime createdAt; 
}
