package br.com.fillipeoliveira.devtask_manager_api.modules.Task.models.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fillipeoliveira.devtask_manager_api.modules.Comment.models.entities.Comment;
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
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "task")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String title;
  private String description;

  @Enumerated(EnumType.STRING)
  private Status status;
  
  @ManyToOne
  @JoinColumn(name = "project_id", nullable = false)
  @JsonIgnore
  private Project project;

  @OneToMany(mappedBy = "task")
  @JsonIgnore
  private List<Comment> comments;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @CreationTimestamp
  private LocalDateTime createdAt; 
}
