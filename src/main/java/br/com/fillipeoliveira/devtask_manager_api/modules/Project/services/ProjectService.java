package br.com.fillipeoliveira.devtask_manager_api.modules.Project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fillipeoliveira.devtask_manager_api.modules.Project.exceptions.ProjectAlreadyExistsException;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.exceptions.ProjectNotFoundException;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.entities.Project;
import br.com.fillipeoliveira.devtask_manager_api.modules.Project.models.repositories.ProjectRepository;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.exceptions.UserNotFoundException;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.repositories.UserRepository;

/**
 * Service responsável pela gestão de projetos. Contém os métodos para criação, atualização,
 * remoção de projetos, além de associar colaboradores a projetos, buscar projetos por ID 
 * e listar projetos de um usuário específico.
 */
@Service
public class ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private UserRepository userRepository;

  /**
   * Cria um novo projeto e associa um usuário como administrador.
   * 
   * @param project  O projeto a ser criado.
   * @param userId  O ID do usuário que será o administrador do projeto.
   * @return O projeto recém-criado.
   * @throws UserNotFoundException Se o usuário não for encontrado.
   * @throws ProjectAlreadyExistsException Se um projeto com o mesmo nome e descrição já existir.
   */
  public Project save(Project project, UUID userId) {
    // Verificar se o usuário existe
    User user = this.userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException());

    // Verificar se o projeto já existe
    this.projectRepository.findByNameAndDescription(project.getName(), project.getDescription())
        .ifPresent(existingProject -> {
          throw new ProjectAlreadyExistsException();
        });

    // Inicializar listas, caso não sejam fornecidas
    project.setTasks(project.getTasks() != null ? project.getTasks() : new ArrayList<>());
    project.setCollaborators(project.getCollaborators() != null ? project.getCollaborators() : new ArrayList<>());

    // Associar o usuário como administrador
    project.setAdmin(user);
    return this.projectRepository.save(project);
  }

  /**
   * Adiciona um colaborador a um projeto.
   * 
   * @param userEmail O e-mail do usuário a ser adicionado.
   * @param projectId O ID do projeto.
   * @return O usuário atualizado com o projeto atribuído.
   * @throws UserNotFoundException Se o usuário não for encontrado.
   * @throws ProjectNotFoundException Se o projeto não for encontrado.
   */
  public User addCollaborator(String userEmail, UUID projectId) {
    User user = this.userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new UserNotFoundException());

    Project project = this.projectRepository.findById(projectId)
        .orElseThrow(() -> new ProjectNotFoundException());

    user.setAssignedProject(project);
    return this.userRepository.save(user);
  }

  /**
   * Retorna todos os projetos de um usuário.
   * 
   * @param userId O ID do usuário.
   * @return A lista de projetos associados ao usuário.
   * @throws UserNotFoundException Se o usuário não for encontrado.
   */
  public List<Project> findAllByUserId(UUID userId) {
    // Verificar se o usuário existe
    this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
    return this.projectRepository.findByAdminId(userId);
  }

  /**
   * Retorna um projeto pelo seu ID.
   * 
   * @param projectId O ID do projeto.
   * @return O projeto correspondente ao ID fornecido.
   * @throws ProjectNotFoundException Se o projeto não for encontrado.
   */
  public Project findById(UUID projectId) {
    return this.projectRepository.findById(projectId)
        .orElseThrow(() -> new ProjectNotFoundException());
  }

  /**
   * Atualiza um projeto existente com os novos valores fornecidos.
   * 
   * @param projectId O ID do projeto a ser atualizado.
   * @param project   O projeto contendo os novos valores.
   * @return O projeto atualizado.
   * @throws ProjectNotFoundException Se o projeto não for encontrado.
   */
  public Project update(UUID projectId, Project project) {
    Project existingProject = this.projectRepository.findById(projectId)
        .orElseThrow(() -> new ProjectNotFoundException());

    // Atualiza campos apenas se não forem nulos
    updateFieldIfNotNull(project.getName(), existingProject::setName);
    updateFieldIfNotNull(project.getDescription(), existingProject::setDescription);
    updateFieldIfNotNull(project.getDueDate(), existingProject::setDueDate);

    return this.projectRepository.save(existingProject);
  }

  /**
   * Exclui um projeto pelo seu ID.
   * 
   * @param projectId O ID do projeto a ser excluído.
   * @throws ProjectNotFoundException Se o projeto não for encontrado.
   */
  public void delete(UUID projectId) {
    this.projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException());
    this.projectRepository.deleteById(projectId);
  }

  /**
   * Atualiza um campo do projeto caso o novo valor não seja nulo.
   * 
   * @param newValue O novo valor a ser setado.
   * @param setter   O setter do campo do projeto.
   */
  private <T> void updateFieldIfNotNull(T newValue, Consumer<T> setter) {
    if (newValue != null) {
      setter.accept(newValue);
    }
  }
}
