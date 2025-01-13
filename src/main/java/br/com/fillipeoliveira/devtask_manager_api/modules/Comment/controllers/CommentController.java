package br.com.fillipeoliveira.devtask_manager_api.modules.Comment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fillipeoliveira.devtask_manager_api.modules.Comment.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
  
  @Autowired
  private CommentService commentService;

}
