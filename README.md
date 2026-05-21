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
- Mapeamento entre camadas (MapStruct e manual)
- Manipulação e transformação de dados (parsing)

## 🚀 Funcionalidades atuais

✔️ CRUD completo de CDs  
✔️ CRUD completo de Vinis  
✔️ CRUD completo de K7s  

✔️ CRUD completo de DVDs  
✔️CRUD completo de Blu-rays

✔️ CRUD completo de Artistas  
✔️ CRUD completo de Diretores

✔️ Relacionamento entre mídias musicais, artistas e faixas  
✔️ Cadastro automático de faixas via parser de texto  
✔️ Associação automática entre mídias e faixas  
✔️ Persistência em cascata de entidades relacionadas

✔️ Conversão DTO ↔ Entity com MapStruct  
✔️ Atualização parcial de entidades com `@MappingTarget`  
✔️ Queries otimizadas com `JOIN FETCH`  
✔️ Estratégia de herança com `JOINED`

✔️ Tratamento de erros com `ResponseStatusException`  
✔️ Documentação interativa com Swagger/OpenAPI  
✔️ Documentação enriquecida com `@Operation` e `@Schema`

---
## 🎵 Funcionalidades do Domínio Musical

### 🎧 CDs, Vinis e K7s
- CRUD completo de mídias musicais
- Cadastro de múltiplas faixas a partir de texto livre (parser)
- Associação automática entre mídia e faixas
- Persistência em cascata (`cascade`)
- Conversão automática de DTOs com MapStruct
- Atualização parcial com `@MappingTarget`
- Identificação automática do tipo de mídia (`tipoMidia`)

### 🔍 Busca de Faixas
- Busca por título (case insensitive)
- Endpoint simples (dados básicos da faixa)
- Endpoint detalhado (inclui CD e artista)
- Query customizada com JOIN FETCH para evitar N+1

### 🎤 Artistas
- CRUD completo de artistas
- Busca por ID com retorno enriquecido (incluindo mídias)
- Busca por nome (parcial e case insensitive)
- Busca por origem (retorno simplificado e ordenado)
- Retorno de mídias com título, ano e tipo (CD, Vinil, etc.)

## 🎬 Funcionalidades do Domínio de Vídeo

### 📀 DVDs e Blu-rays
- CRUD completo de DVDs
- CRUD completo de Blu-rays
- Associação de múltiplos diretores via relacionamento ManyToMany
- Conversão automática de DTOs com MapStruct
- Atualização parcial com `@MappingTarget`
- Identificação automática do tipo de mídia (`tipoMidia`)
- Queries otimizadas com `JOIN FETCH`

### 🎬 Diretores
- CRUD completo de diretores
- Busca por ID com retorno enriquecido (incluindo mídias)
- Busca por nome (parcial e case insensitive)
- Busca por nacionalidade (retorno simplificado e ordenado)
- Associação entre diretores e mídias de vídeo
- Retorno de mídias com título, ano e tipo (DVD, Blu-ray, etc.)
- Queries otimizadas com `JOIN FETCH`

### 🧠 Estratégias de Mapeamento
- Uso de MapStruct para conversões simples e atualizações parciais
- Uso de mapper manual para cenários específicos
- Conversão automática de listas e relacionamentos
---

## 🧠 Domínio da aplicação

O sistema é dividido em dois principais domínios:

- 🎵 Mídias Musicais (CD, Vinil, K7)
- 🎬 Mídias de Vídeo (DVD e Blu-ray)

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
│   ├── dto                        // MidiaResumoDTO
│   ├── musical
│   │   ├── controller             // CDController, VinilController, K7Controller
│   │   ├── service                // CDService, VinilService, K7Service
│   │   ├── repository             // CDRepository, VinilRepository, K7Repository
│   │   ├── domain                 // MidiaMusical, CD, Vinil, K7
│   │   ├── dto                    // CDRequestDTO, CDResponseDTO
│   │   ├── mapstruct              // CDMapper
│   │   └── enums                  // GeneroMusical, CategoriaDisco, etc.
│   └── video
│       ├── controller             // DVDController, BlurayController
│       ├── service                // DVDService, BlurayService
│       ├── repository             // DVDRepository, BlurayRepository
│       ├── domain                 // MidiaVideo, DVD, Bluray
│       ├── dto                    // DVDRequestDTO, DVDResponseDTO, BlurayRequestDTO, BlurayResponseDTO
│       ├── mapstruct              // DVDMapper, BlurayMapper
│       └── enums                  // GeneroFilme, CategoriaVideo, etc.
├── artista
│   ├── controller                 // ArtistaController
│   ├── service                    // ArtistaService
│   ├── repository                 // ArtistaRepository
│   ├── domain                     // entidade Artista
│   ├── dto                        // ArtistaRequestDTO, ArtistaResponseDTO
│   └── mapper                     // ArtistaMapper 
├── diretor
│   ├── controller                 // DiretorController
│   ├── service                    // DiretorService
│   ├── repository                 // DiretorRepository
│   ├── domain                     // entidade Diretor
│   ├── dto                        // DiretorRequestDTO, DiretorResponseDTO
│   └── mapper                     // DiretorMapper             
```
## 🏛️ Arquitetura e Boas Práticas

O projeto utiliza uma arquitetura baseada em separação por domínio (*package by feature*), organizada em camadas:

- Controller
- Service
- Repository
- DTO
- MapStruct e mappers manuais
- Domain

Além disso, o sistema aplica conceitos importantes de desenvolvimento backend:

- Herança com JPA (`JOINED`)
- DTO Pattern
- Mapper Pattern
- Parser de texto para entidades relacionadas
- Queries otimizadas com `JOIN FETCH`
- Estratégias para evitar problema N+1
- Tratamento centralizado de erros
- Transações com `@Transactional`
- Documentação com Swagger/OpenAPI
- Relacionamentos ManyToMany entre entidades

## 🏗️ Tecnologias utilizadas

- Java 21
- Spring Boot 3.5.x
- Spring Data JPA
- PostgreSQL
- Maven
- Hibernate / JPA
- Swagger / OpenAPI

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