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


CREATE TABLE IF NOT EXISTS brand (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    active BOOLEAN DEFAULT TRUE
);
CREATE SEQUENCE IF NOT EXISTS brand_id_seq;


INSERT INTO brand (name, description) VALUES
    ('SAMSUNG', 'Marca de eletronicos'),
    ('Editora Spring', 'Maior editora do universo SPRING'),
    ('Tommy', 'Marca de vestuário em geral ');

CREATE TABLE Product (
    id SERIAL PRIMARY KEY,
    sku VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    category_id INTEGER REFERENCES Category(id),
    brand_id INTEGER REFERENCES Brand(id),
    price DECIMAL(10, 2) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE SEQUENCE product_sequence;

INSERT INTO product (id, sku, name, category_id, brand_id, price) VALUES
    (NEXTVAL('product_id_seq'), 'SKU1', 'Smart TV 40 pol', 1, 1, 10.50),
    (NEXTVAL('product_id_seq'), 'SKU2', '48 leis do poder', 3, 2, 35.75),
    (NEXTVAL('product_id_seq'), 'SKU3', 'O poder do subconsciente', 3, 2, 20.00),
    (NEXTVAL('product_id_seq'), 'SKU4', 'Camisa Polo', 2, 3, 300.25);