package br.com.fillipeoliveira.devtask_manager_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
  
  private String title = "DevTask_Manager API";
  private String description = "API responsável pela gestão de projetos, tarefas, comentários e colaboração entre usuários.";
  private String version = "1.0";  

  @Bean
  OpenAPI openAPI() {
    return new OpenAPI()
        .info(
          new Info()
              .title(title)
              .description(description)
              .version(version)
        );
  }  
}