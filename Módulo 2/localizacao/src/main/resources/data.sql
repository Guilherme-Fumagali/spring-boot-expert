CREATE TABLE cidade (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    qtd_habitantes BIGINT
);

INSERT INTO cidade (id, nome, qtd_habitantes) VALUES 
    (1, 'São Paulo', 12000000),
    (2, 'Rio de Janeiro', 10000000),
    (3, 'Fortaleza', 8000000);
