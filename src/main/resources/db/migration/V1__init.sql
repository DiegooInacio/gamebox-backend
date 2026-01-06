CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    imagem_perfil VARCHAR(255),
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP
);

CREATE TABLE categoria (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE jogo (
    id BIGSERIAL PRIMARY KEY,
    external_api_id VARCHAR(100) NOT NULL UNIQUE,
    nome VARCHAR(200) NOT NULL,
    imagem_capa VARCHAR(255),
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP
);

CREATE TABLE jogo_categoria (
    jogo_id BIGINT NOT NULL,
    categoria_id BIGINT NOT NULL,

    PRIMARY KEY (jogo_id, categoria_id),

    CONSTRAINT fk_jogo_categoria_jogo
        FOREIGN KEY (jogo_id)
        REFERENCES jogo (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_jogo_categoria_categoria
        FOREIGN KEY (categoria_id)
        REFERENCES categoria (id)
        ON DELETE CASCADE
);

CREATE TABLE biblioteca (
    usuario_id BIGINT NOT NULL,
    jogo_id BIGINT NOT NULL,
    adicionado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (usuario_id, jogo_id),

    CONSTRAINT fk_biblioteca_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuario (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_biblioteca_jogo
        FOREIGN KEY (jogo_id)
        REFERENCES jogo (id)
        ON DELETE CASCADE
);

CREATE TABLE avaliacao (
   id BIGSERIAL PRIMARY KEY,
   usuario_id BIGINT NOT NULL,
   jogo_id BIGINT NOT NULL,
   nota NUMERIC(2,1) NOT NULL CHECK (nota BETWEEN 1 AND 5),
   comentario TEXT,
   criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   atualizado_em TIMESTAMP,

   CONSTRAINT uk_usuario_jogo UNIQUE (usuario_id, jogo_id),

   CONSTRAINT fk_avaliacao_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuario (id)
        ON DELETE CASCADE,

   CONSTRAINT fk_avaliacao_jogo
        FOREIGN KEY (jogo_id)
        REFERENCES jogo (id)
        ON DELETE CASCADE
);

CREATE INDEX idx_jogo_nome ON jogo (nome);
CREATE INDEX idx_biblioteca_usuario ON biblioteca (usuario_id);
CREATE INDEX idx_biblioteca_jogo ON biblioteca (jogo_id);
CREATE INDEX idx_avaliacao_jogo ON avaliacao (jogo_id);
CREATE INDEX idx_avaliacao_usuario ON avaliacao (usuario_id);