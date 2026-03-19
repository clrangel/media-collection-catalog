# 📚 Media Collection Catalog

API desenvolvida em Java com Spring Boot para gerenciamento de um catálogo de mídias físicas, como discos musicais e filmes.

## 📊 Diagrama de Classes

Abaixo está o Diagrama de Classes que representa a modelagem completa do domínio da aplicação:

![Diagrama de Classes](assets/diagrams/diagrama-classes.png)

## 🎯 Objetivo

Este projeto foi criado com o objetivo de praticar e demonstrar conceitos fundamentais de desenvolvimento backend, com foco em:

- Modelagem de domínio
- Relacionamentos entre entidades
- Arquitetura em camadas
- Boas práticas com Spring Boot

## 🧠 Domínio da aplicação

O sistema é dividido em dois principais domínios:

- 🎵 Mídias Musicais (CD, Vinil, K7)
- 🎬 Mídias de Vídeo (DVD, Blu-ray)

A modelagem utiliza herança para promover reutilização de código e melhor organização das entidades.

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