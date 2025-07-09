CREATE TABLE tb_expense (
    id_expense BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(200) NOT NULL,
    amount NUMERIC(19,2) NOT NULL,
    date VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL
);