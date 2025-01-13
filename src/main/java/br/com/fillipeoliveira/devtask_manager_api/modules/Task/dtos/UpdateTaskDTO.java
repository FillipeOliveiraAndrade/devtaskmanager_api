package br.com.fillipeoliveira.devtask_manager_api.modules.Task.dtos;

import br.com.fillipeoliveira.devtask_manager_api.modules.Task.enums.Status;

/**
 * DTO utilizado para representar os dados de atualização de uma tarefa.
 * Esse DTO é utilizado quando se deseja atualizar o status de uma tarefa
 * já existente no sistema.
 * 
 * @param status O novo status da tarefa, que pode ser um dos valores
 *               definidos no enum {@link Status}.
 */
public record UpdateTaskDTO(
  Status status 
) {}
