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
- Mapeamento entre camadas com MapStruct

## 🚀 Funcionalidades atuais

✔️ CRUD completo de CDs  
✔️ Cadastro de Artistas  
✔️ Relacionamento entre CD e Artista  
✔️ Conversão DTO ↔ Entity com MapStruct  
✔️ Queries otimizadas com JOIN FETCH  
✔️ Tratamento de erros com ResponseStatusException  
✔️ Documentação interativa com Swagger (http://localhost:8080/swagger-ui.html)

---

## 🧠 Domínio da aplicação

O sistema é dividido em dois principais domínios:

- 🎵 Mídias Musicais (CD, Vinil, K7)
- 🎬 Mídias de Vídeo (DVD, Blu-ray)

A modelagem utiliza herança para promover reutilização de código e melhor organização das entidades.

## 🧱 Estrutura do Projeto

O projeto segue uma abordagem híbrida:

👉 Organização por domínio + separação em camadas

Exemplo (domínio musical):

midia/musical/
- controller
- service
- repository
- domain
- dto
- mapstruct
- enums

##### 💿 Midia (classe base abstrata que representa qualquer tipo de mídia)

---
### 🎵 Domínio de Mídias Musicais
- **MidiaMusical** (especialização de Midia para música)
- **Artista** (relacionamento 1 com mídias musicais)
- **Faixa** (entidade dependente de MidiaMusical)

##### 🎧 Especializações
- CD
- Vinil
- K7

##### 🎵 Enums
- **GeneroMusical** → representa os estilos musicais
- **CategoriaDisco** → classifica o tipo de mídia (álbum, EP, etc.)
- **FormatoDisco** → representa o formato do CD (simples, duplo, box, etc.)
- **TipoVinil** → representa característica física do vinil (preto, colorido, picture, etc.)
- **TipoCD** → representa característica física da caixa do CD (jewel case, digipack, slipcase, etc.)
---

### 🎬 Domínio de Mídias de Vídeo
- **MidiaVideo** (especialização de Midia)
- **Diretor** (relacionamento N com mídias de vídeo)

##### 🎬 Especializações
- DVD
- Blu-ray

##### 🎬 Enums
- **GeneroFilme** → representa os gêneros cinematográficos
- **CategoriaVideo** → classifica o tipo de conteúdo (filme, série, etc.)
- **TipoEdicao** → simples, duplo ou box
- **Resolucao** → HD, Full HD, 4K, 8K (aplicado a Blu-ray)


### 📦 Organização

A estrutura segue o modelo *package by feature*, separando claramente os domínios de mídia musical e vídeo, além das entidades relacionadas:

```
br.com.catalogo.mediacollectioncatalog
├── midia
│   ├── domain                     // classe base Midia
│   ├── musical
│   │   ├── controller             // CDController
│   │   ├── service                // CDService
│   │   ├── repository             // CDRepository
│   │   ├── domain                 // MidiaMusical, CD, Vinil, K7
│   │   ├── dto                    // CDRequestDTO, CDResponseDTO
│   │   ├── mapstruct              // CDMapper
│   │   └── enums                  // GeneroMusical, CategoriaDisco, etc.
│   └── video
│       ├── domain                 // MidiaVideo, DVD, Blu-ray
│       └── enums                  // GeneroFilme, CategoriaVideo, etc.
├── artista
│   ├── controller                 // ArtistaController
│   ├── service                    // ArtistaService
│   ├── repository                 // ArtistaRepository
│   ├── domain                     // entidade Artista
│   └── dto                        // ArtistaRequestDTO, ArtistaResponseDTO
└── diretor
    └── domain                     // entidade Diretor
```

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
- MapStruct
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