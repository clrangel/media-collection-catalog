CREATE TABLE historico_midia (
    id BIGSERIAL PRIMARY KEY,
    midia_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    data_hora TIMESTAMP WITH TIME ZONE NOT NULL
);