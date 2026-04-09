-- MidiaVideo (herança JOINED)
CREATE TABLE midias_videos (
    id BIGINT PRIMARY KEY,
    sinopse VARCHAR(250) NOT NULL,
    atores VARCHAR(150) NOT NULL,
    roteiro VARCHAR(100) NOT NULL,
    genero VARCHAR(50) NOT NULL,
    categoria_video VARCHAR(50) NOT NULL,
    pais_filme VARCHAR(30),

    CONSTRAINT fk_video_midia FOREIGN KEY (id)
        REFERENCES midias(id) ON DELETE CASCADE
);

-- Diretores
CREATE TABLE diretores (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    nacionalidade VARCHAR(30)
);

-- Tabela de junção (ManyToMany)
CREATE TABLE midias_videos_diretores (
    midia_video_id BIGINT NOT NULL,
    diretor_id BIGINT NOT NULL,

    CONSTRAINT pk_midias_videos_diretores
        PRIMARY KEY (midia_video_id, diretor_id),

    CONSTRAINT fk_mvd_video
        FOREIGN KEY (midia_video_id)
        REFERENCES midias_videos(id) ON DELETE CASCADE,

    CONSTRAINT fk_mvd_diretor
        FOREIGN KEY (diretor_id)
        REFERENCES diretores(id) ON DELETE CASCADE
);