CREATE TABLE category (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    active BOOLEAN DEFAULT TRUE
);

CREATE SEQUENCE category_sequence;


INSERT INTO category (id, name, description, active) VALUES
    (1, 'Eletrônicos', 'Categoria para produtos eletrônicos', TRUE),
    (2, 'Roupas', 'Categoria para roupas e acessórios', TRUE),
    (3, 'Livros', 'Categoria para livros de diversas áreas', TRUE);
