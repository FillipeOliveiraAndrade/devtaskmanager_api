package br.com.fillipeoliveira.devtask_manager_api.modules.User.services;

import java.io.IOException;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.fillipeoliveira.devtask_manager_api.modules.User.exceptions.EmailAlreadyExistsException;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.exceptions.UserNotFoundException;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.entities.User;
import br.com.fillipeoliveira.devtask_manager_api.modules.User.models.repositories.UserRepository;
import jakarta.transaction.Transactional;

/**
 * Service responsável pela gestão de usuários. Contém os métodos para criação, atualização,
 * obtenção de usuários, e manipulação de avatares.
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * Salva um novo usuário no sistema.
   * 
   * @param user O usuário a ser salvo.
   * @return O usuário salvo com a senha criptografada.
   * @throws EmailAlreadyExistsException Caso o e-mail já esteja cadastrado.
   */
  public User save(User user) {
    this.userRepository.findByEmail(user.getEmail()).ifPresent(existingUser -> {
      throw new EmailAlreadyExistsException();
    });

    var password = this.passwordEncoder.encode(user.getPassword());
    user.setPassword(password);
    return this.userRepository.save(user);
  }

  /**
   * Atualiza as informações de um usuário existente.
   * 
   * @param userId O ID do usuário a ser atualizado.
   * @param user O novo usuário com as informações a serem atualizadas.
   * @return O usuário atualizado.
   * @throws UserNotFoundException Caso o usuário não seja encontrado.
   * @throws EmailAlreadyExistsException Caso o novo e-mail já exista no sistema.
   */
  public User update(UUID userId, User user) {
    User existingUser = this.userRepository.findById(userId).orElseThrow(
        UserNotFoundException::new);

    updateFieldIfNotNull(user.getName(), existingUser::setName);
    updateFieldIfNotNull(user.getEmail(), existingUser::setEmail);

    if (!user.getEmail().equals(existingUser.getEmail())) {
      this.userRepository.findByEmail(user.getEmail()).ifPresent(existing -> {
        throw new EmailAlreadyExistsException();
      });
    }

    return this.userRepository.save(existingUser);
  }

  /**
   * Busca um usuário pelo seu ID.
   * 
   * @param userId O ID do usuário a ser buscado.
   * @return O usuário encontrado.
   * @throws UserNotFoundException Caso o usuário não seja encontrado.
   */
  public User findById(UUID userId) {
    return this.userRepository.findById(userId)
        .orElseThrow(UserNotFoundException::new);
  }

  /**
   * Atualiza o avatar de um usuário.
   * 
   * @param userId O ID do usuário cujo avatar será atualizado.
   * @param avatar O novo avatar do usuário (arquivo de imagem).
   * @return O usuário com o avatar atualizado.
   * @throws IOException Caso ocorra algum erro durante o processamento do arquivo de imagem.
   * @throws UserNotFoundException Caso o usuário não seja encontrado.
   */
  @Transactional
  public User updateAvatar(UUID userId, MultipartFile avatar) throws IOException {
    User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    user.setAvatar(avatar.getBytes());
    return this.userRepository.save(user);
  }

  /**
   * Retorna o avatar de um usuário.
   * 
   * @param userId O ID do usuário.
   * @return O avatar do usuário em formato de bytes.
   * @throws UserNotFoundException Caso o usuário não seja encontrado.
   */
  public byte[] getAvatar(UUID userId) {
    User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    return user.getAvatar();
  }

  /**
   * Atualiza um campo de um usuário se o valor não for nulo.
   * 
   * @param newValue O novo valor para o campo.
   * @param setter O setter do campo a ser atualizado.
   * @param <T> Tipo do campo a ser atualizado.
   */
  private <T> void updateFieldIfNotNull(T newValue, Consumer<T> setter) {
    if (newValue != null) {
      setter.accept(newValue);
    }
  }
}
