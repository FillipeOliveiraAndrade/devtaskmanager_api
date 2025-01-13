# DevTask Manager API

### Descrição
A DevTask Manager API é uma aplicação desenvolvida em Java utilizando o framework Spring Boot. Esta API tem como objetivo gerenciar projetos, tarefas, comentários e colaboradores, oferecendo funcionalidades robustas para a administração e organização de atividades em equipe.

### Principais Funcionalidades
- **Gestão de Usuários:** Criação, atualização, autenticação e gerenciamento de avatares.
- **Gestão de Projetos:** Criação, atualização, exclusão e associação de colaboradores a projetos.
- **Gestão de Tarefas:** Criação, atualização de status e exclusão de tarefas vinculadas a projetos.
- **Gestão de Comentários:** Adição de comentários em tarefas específicas.
- **Autenticação JWT:** Implementação de segurança utilizando JSON Web Tokens para proteger as rotas da API.

### Tecnologias Utilizadas
- **Linguagem:** Java 17
- **Framework:** Spring Boot 3.4.1
- **Banco de Dados:** PostgreSQL
- **Segurança:** Spring Security com JWT
- **Documentação:** Swagger/OpenAPI
- **Dependências:**
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Web
  - Spring Boot Starter Security
  - Spring Boot DevTools
  - Springdoc OpenAPI
  - Lombok
  - Java JWT

### Pré-requisitos
Antes de executar a aplicação, certifique-se de ter instalado:
- Docker e Docker Compose
- Java 17+
- Maven

### Configuração do Ambiente
1. Clone este repositório:
   ```bash
   git clone https://github.com/FillipeOliveiraAndrade/devtaskmanager_api.git
   cd devtaskmanager_api
   ```

2. Configure o banco de dados PostgreSQL utilizando o arquivo `docker-compose.yml`:
   ```bash
   docker-compose up -d
   ```

3. Configure as variáveis de ambiente no arquivo `application.properties`.
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/devtaskmanagerDB
   spring.datasource.username=admin
   spring.datasource.password=admin
   api.security.token.secret=s3cr3t
   ```

4. Compile e execute o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```

5. Acesse a documentação da API no navegador:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

### Estrutura do Projeto
```
fillipeoliveiraandrade-devtaskmanager_api/
├── docker-compose.yml
├── mvnw
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/fillipeoliveira/devtask_manager_api/
│   │   │       ├── DevtaskManagerApiApplication.java
│   │   │       ├── config/
│   │   │       │   └── SwaggerConfig.java
│   │   │       ├── exceptions/
│   │   │       ├── modules/
│   │   │       │   ├── Comment/
│   │   │       │   ├── Project/
│   │   │       │   ├── Task/
│   │   │       │   └── User/
│   │   │       └── security/
│   │   └── resources/
│   └── test/
└── .mvn/
```

### Melhorias Futuras
- Implementação de testes unitários para garantir a qualidade do código.
- Criação de testes de integração para verificar o funcionamento completo das funcionalidades da API.

### Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests com melhorias ou correções.

### Licença
Este projeto é distribuído sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.
