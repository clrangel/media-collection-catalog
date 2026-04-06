# 📚 Media Collection Catalog

API desenvolvida em Java com Spring Boot para gerenciamento de um catálogo de mídias físicas, como discos musicais e filmes.

## 📊 Diagrama de Classes

Abaixo está o Diagrama de Classes que representa a modelagem completa do domínio da aplicação:

![Diagrama de Classes](assets/diagrams/diagrama-classes.png)

## 🎯 Objetivo

Este projeto foi criado com o objetivo de praticar e demonstrar conceitos fundamentais de desenvolvimento backend, com foco em:

- Modelagem de domínio
- Relacionamentos entre entidades
- Arquitetura orientada a domínio (DDD)
- Boas práticas com Spring Boot

## 🧠 Domínio da aplicação

O sistema é dividido em dois principais domínios:

- 🎵 Mídias Musicais (CD, Vinil, K7)
- 🎬 Mídias de Vídeo (DVD, Blu-ray)

A modelagem utiliza herança para promover reutilização de código e melhor organização das entidades.

## 🧱 Estrutura do Projeto

O projeto está sendo desenvolvido seguindo o padrão **DDD (Domain-Driven Design)** com organização por domínio.

Atualmente, o domínio de mídias musicais possui as seguintes entidades:

- **Midia** (classe base abstrata)
- **MidiaMusical** (especialização para músicas)
- **Artista** (relacionamento 1:N com mídias musicais)
- **Faixa** (entidade dependente de MidiaMusical)

### 🎵 Enums

- **GeneroMusical** → representa os estilos musicais
- **CategoriaDisco** → classifica o tipo de mídia (álbum, EP, etc.)

### 📦 Organização

A estrutura segue o modelo *package by feature*:

    br.com.catalogo.mediacollectioncatalog
    ├── midia
    ├── artista

## 🏗️ Tecnologias utilizadas

- Java 21
- Spring Boot 3.5.x
- Spring Data JPA
- PostgreSQL
- Maven

---

### 🔧 Dependências
- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Validation
- Flyway Migration
- Lombok
- OpenAPI (Swagger)

## 🗄️ Configuração do Banco de Dados

Para executar o projeto, é necessário ter um banco PostgreSQL configurado.

Atualize as propriedades no arquivo `application.properties` com suas credenciais:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/collection_catalog_db
spring.datasource.username=SEU_LOGIN
spring.datasource.password=SUA_SENHA
```
> ⚠️ Observação:
> A propriedade `spring.jpa.hibernate.ddl-auto` está comentada para evitar falhas na inicialização, já que o banco ainda não possui estrutura definida e será versionado futuramente com Flyway.
---
## 🚀 Como executar o projeto

```bash
# Clonar repositório
git clone https://github.com/clrangel/media-collection-catalog.git

# Entrar no diretório
cd media-collection-catalog

# Rodar a aplicação

# Linux/Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```