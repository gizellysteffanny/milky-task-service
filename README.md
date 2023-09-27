# 📝 Milky Task Service

O Milky Task Service é um microserviço dedicado à gestão de tarefas no aplicativo Milky Taskscape.

## 🛠️ Tecnologias Utilizadas

- Linguagem de Programação: Java
- Framework: Spring Boot
- Banco de Dados: MongoDB

## 🚀 Funcionalidades

- Criação de Tarefas
- Atualização de Tarefas
- Exclusão de Tarefas
- Listagem de Tarefas
- Consulta de Tarefas por ID

## ✅ Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas antes de prosseguir:

- Java Development Kit (JDK) 8 ou superior
- Docker (para configurar o ambiente com Docker Compose)

## 📦 Instalação

1. Clone este repositório para a sua máquina local.
2. Execute `mvn clean install` para instalar as dependências.
3. Configure as propriedades do MongoDB em `src/main/resources/application.properties`.
4. Configure o banco de dados MongoDB com Docker Compose:
    - Abra um terminal na pasta raiz do projeto e execute o comando:
      ```sh
        docker-compose up -d
      ```
    - Para acessar o banco com o Studio 3T:
      ```
      mongodb://milky_task_db_user:PASSWORD@localhost:27017/milky_task_db?authSource=admin
      ```
5. Execute `./mvnw spring-boot:run` para iniciar o microserviço.

## 🌐 Endpoints

- `GET /tasks` - Listar todas as tarefas.
- `GET /tasks/{taskId}` - Obter detalhes de uma tarefa específica.
- `POST /tasks` - Criar uma nova tarefa.
- `PUT /tasks/{taskId}` - Atualizar uma tarefa existente.
- `DELETE /tasks/{taskId}` - Excluir uma tarefa.

## 🔒 Segurança

Este microserviço implementa medidas de segurança para proteger os dados das tarefas.

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests.

## 📄 Licença

Este projeto está licenciado sob a [licença MIT](LICENSE).

---

**Importante:**  Este microserviço é parte do aplicativo Milky Taskscape. Certifique-se de que você também tem o microserviço de autenticação configurado corretamente para uma experiência completa do aplicativo.