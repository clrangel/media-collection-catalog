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

### 🔐 Login e Autenticação
- Autenticação de usuários;
- Geração de tokens JWT para usuários autenticados.

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

### 🔐 Login e Autenticação

- Spring Security
- OAuth2 Resource Server

### ☁️ Comunicação e Descoberta de Serviços

- Spring Cloud Netflix Eureka
- Spring Cloud OpenFeign


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

A senha do usuário é armazenada de forma segura utilizando criptografia antes de ser persistida no banco de dados.

O campo `dataCadastro` é preenchido automaticamente no momento da persistência da entidade.

## 🌐 Endpoints disponíveis

### 🔐 Login e Autenticação

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/auth/login` | Autentica o usuário e retorna um token JWT |

### Usuários

| Método | Endpoint | Descrição |
|---|---|--|
| POST | `/usuarios` | Cadastra um usuário |
| GET | `/usuarios` | Lista todos os usuários |
| GET | `/usuarios/{id}` | Busca usuário por ID |
| GET | `/usuarios/email/{email}` | Busca dados resumidos do usuário por e-mail |
| PUT | `/usuarios/{id}` | Atualiza um usuário |
| DELETE | `/usuarios/{id}` | Remove um usuário |

## 🔐 Login e Autenticação

O serviço é responsável por autenticar os usuários através de e-mail e senha.

Após uma autenticação bem-sucedida, o serviço gera um token JWT contendo as informações necessárias para identificar o usuário autenticado, incluindo seu ID através do claim `userId`.

O token também possui informações relacionadas à sua emissão e expiração, permitindo que os demais microsserviços validem sua autenticidade e utilizem a identificação do usuário durante as requisições protegidas.

## ▶️ Executando o projeto

### Pré-requisitos

- Java 21
- Maven
- PostgreSQL

### Configuração do banco

Configure as propriedades do banco no arquivo:

`src/main/resources/application.properties`


Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/user_service_db
spring.datasource.username=SEU_LOGIN
spring.datasource.password=SUA_SENHA
```

### Configuração do JWT

O serviço utiliza uma chave secreta para a geração dos tokens JWT.

Configure a propriedade `jwt.secret` no arquivo `src/main/resources/application.properties`.

Exemplo:

```properties
jwt.secret=SUA_CHAVE_SECRETA
``` 