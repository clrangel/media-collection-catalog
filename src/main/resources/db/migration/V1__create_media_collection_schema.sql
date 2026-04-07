-- Tabela base (Midia)
CREATE TABLE midias (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    ano_lancamento INTEGER,
    data_cadastro TIMESTAMP NOT NULL
);

-- Artista
CREATE TABLE artistas (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    origem VARCHAR(30)
);

-- MidiaMusical (herança JOINED)
CREATE TABLE midias_musicais (
    id BIGINT PRIMARY KEY,
    genero VARCHAR(50) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    descricao VARCHAR(250) NOT NULL,
    artista_id BIGINT NOT NULL,

    CONSTRAINT fk_midia FOREIGN KEY (id) REFERENCES midias(id) ON DELETE CASCADE,
    CONSTRAINT fk_artista FOREIGN KEY (artista_id) REFERENCES artistas(id)
);

-- CD
CREATE TABLE cds (
    id BIGINT PRIMARY KEY,
    numero_discos INTEGER NOT NULL,
    formato_disco VARCHAR(50) NOT NULL,
    tipo_cd VARCHAR(50) NOT NULL,

    CONSTRAINT fk_cd_midia FOREIGN KEY (id) REFERENCES midias_musicais(id) ON DELETE CASCADE
);

-- Vinil
CREATE TABLE vinis (
    id BIGINT PRIMARY KEY,
    numero_discos INTEGER NOT NULL,
    formato_disco VARCHAR(50) NOT NULL,
    tipo_vinil VARCHAR(50) NOT NULL,

    CONSTRAINT fk_vinil_midia FOREIGN KEY (id) REFERENCES midias_musicais(id) ON DELETE CASCADE
);

-- K7
CREATE TABLE k7s (
    id BIGINT PRIMARY KEY,

    CONSTRAINT fk_k7_midia FOREIGN KEY (id) REFERENCES midias_musicais(id) ON DELETE CASCADE
);

-- Faixas
CREATE TABLE faixas (
    id BIGSERIAL PRIMARY KEY,
    numero INTEGER NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    midia_musical_id BIGINT NOT NULL,

    CONSTRAINT fk_faixa_midia FOREIGN KEY (midia_musical_id) REFERENCES midias_musicais(id) ON DELETE CASCADE
);