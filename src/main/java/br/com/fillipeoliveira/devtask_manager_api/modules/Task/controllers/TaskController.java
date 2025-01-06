package br.com.fillipeoliveira.devtask_manager_api.modules.Task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fillipeoliveira.devtask_manager_api.modules.Task.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  
  @Autowired
  private TaskService taskService;
}
