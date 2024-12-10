# Aplicação Scolaris

Scolaris é uma aplicação Spring Boot projetada para gerenciar dados relacionados à escola, como turmas, usuários, testes e notas. A aplicação fornece APIs RESTful para criar, atualizar, recuperar e excluir essas entidades. É meu primeiro repositório público e será usado de base para os projetos de front-end que farei, então espere mudanças no futuro.
## Tecnologias
Java

Spring Boot

Maven

MySQL
## Começando
### Pré-requisitos
- Java 11 ou superior
- Maven 3.6.0 ou superior
- MySQL 5.7 ou superior

### Instalação

#### Clone o repositório:

```bash
git clone https://github.com/tavepe/scolaris.git
```

#### Navegue até o diretório do projeto:

```bash
cd scolaris
```

#### Configure o banco de dados:

1. Instale o MySQL e inicie o servidor MySQL.
2. Crie um novo banco de dados:

```sql
CREATE DATABASE Scolaris;
```

3. Atualize a configuração do banco de dados em `src/main/resources/application.properties` com seu nome de usuário e senha do MySQL:

```ini
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/Scolaris
spring.datasource.username=seu_usuario_mysql
spring.datasource.password=sua_senha_mysql
```

#### Construa o projeto usando Maven:

```bash
mvn clean install
```

#### Navegue até o pacote do projeto:

```bash
cd target
```

#### Execute a aplicação:

```bash
java -jar .\Scolaris-1.0.0.jar
```

A aplicação será iniciada em [http://localhost:8080](http://localhost:8080).

## Endpoints da API

### Gerenciamento de Usuários

POST /createUser - Criar um novo usuário

GET /listUsers - Listar todos os usuários

GET /getUser/{id} - Recuperar usuário por ID

GET /listStudents - Listar todos os usuários estudantes

GET /listTeachers - Listar todos os usuários professores

PUT /updateUser/{id} - Atualizar um usuário existente

DELETE /deleteUser/{id} - Excluir um usuário

### Gerenciamento de Turmas

POST /createClass - Criar uma nova turma

GET /listClasses - Listar todas as turmas

GET /getClass/{id} - Recuperar uma turma por ID

GET /listClassesByTeacher/{id} - Listar todas as turmas por ID do professor

PUT /updateClass/{id} - Atualizar uma turma existente

DELETE /deleteClass/{id} - Excluir uma turma

### Gerenciamento de Testes

POST /createTest - Criar um novo teste

GET /getTest/{id} - Recuperar um teste por ID

GET /listTestsByClass/{id} - Listar todos os testes por ID da turma

### Gerenciamento de Notas
POST /createGrade - Criar uma nova nota

GET /getGrade/{studentId}/{testId} - Recuperar uma nota por ID do estudante e ID do teste

## Licença

Este projeto é licenciado sob a Licença MIT.  <hr></hr>

## Outros Idiomas

[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/tavepe/Scolaris/blob/main/README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/tavepe/Scolaris/blob/main/README.pt-br.md)