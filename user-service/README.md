# 👤 User Service

Microsserviço responsável pelo gerenciamento de usuários da aplicação **Media Collection Catalog**.

Este serviço faz parte da evolução da aplicação para uma arquitetura baseada em microsserviços, possuindo responsabilidade própria e banco de dados independente.

## 🎯 Responsabilidades

Atualmente o serviço é responsável por:

- Cadastro de usuários;
- Consulta de usuários por ID;
- Consulta de usuários por e-mail;
- Atualização de usuários;
- Exclusão de usuários;
- Persistência dos dados dos usuários.

## 🛠️ Tecnologias utilizadas

- Java 21
- Spring Boot 3.5.12
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Flyway
- Bean Validation
- Lombok
- MapStruct
- Maven
- Springdoc OpenAPI

## 🗄️ Banco de dados

O serviço utiliza um banco PostgreSQL próprio: 

```
user_service_db
```

A persistência é realizada utilizando JPA/Hibernate e as alterações de estrutura do banco são gerenciadas através do Flyway.

## 🧱 Modelo de dados

A entidade `Usuario` possui os seguintes atributos:

- `id`
- `nome`
- `email`
- `senha`
- `dataCadastro`

O campo `dataCadastro` é preenchido automaticamente no momento da persistência da entidade.

## 🌐 Endpoints disponíveis

### Usuários

| Método | Endpoint | Descrição |
|---|---|--|
| POST | `/usuarios` | Cadastra um usuário |
| GET | `/usuarios` | Lista todos os usuários |
| GET | `/usuarios/{id}` | Busca usuário por ID |
| GET | `/usuarios/email/{email}` | Busca dados resumidos do usuário por e-mail |
| PUT | `/usuarios/{id}` | Atualiza um usuário |
| DELETE | `/usuarios/{id}` | Remove um usuário |

## ▶️ Executando o projeto

### Pré-requisitos

- Java 21
- Maven
- PostgreSQL

### Configuração do banco

Configure as propriedades do banco no arquivo:


Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/user_service_db
spring.datasource.username=SEU_LOGIN
spring.datasource.password=SUA_SENHA
```