-- DVD
CREATE TABLE dvds (
    id BIGINT PRIMARY KEY,
    numero_discos INTEGER NOT NULL,
    tipo_edicao VARCHAR(50) NOT NULL,

    CONSTRAINT fk_dvd_midia_video
        FOREIGN KEY (id)
        REFERENCES midias_videos(id) ON DELETE CASCADE
);

-- Bluray
CREATE TABLE blurays (
    id BIGINT PRIMARY KEY,
    numero_discos INTEGER NOT NULL,
    tipo_edicao VARCHAR(50) NOT NULL,
    resolucao VARCHAR(50) NOT NULL,

    CONSTRAINT fk_bluray_midia_video
        FOREIGN KEY (id)
        REFERENCES midias_videos(id) ON DELETE CASCADE
);