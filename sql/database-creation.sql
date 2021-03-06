DROP TABLE STORES CASCADE;
DROP TABLE PRODUCTS CASCADE;
DROP TABLE INVENTORY CASCADE;

CREATE TABLE IF NOT EXISTS stores (
	id SERIAL,
	nome VARCHAR(255) NOT NULL,
	document INTEGER NOT NULL,
	PRIMARY KEY (id)
);

CREATE UNIQUE INDEX CONCURRENTLY store_document 
ON stores (document);

CREATE TABLE IF NOT EXISTS products (
    id SERIAL,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX CONCURRENTLY nome 
ON products (nome);

CREATE TABLE IF NOT EXISTS inventory (
    id SERIAL,
    product_serie INTEGER NOT NULL,
	product_id INTEGER NOT NULL,
    store_document INTEGER NOT NULL,
    amount INTEGER NOT NULL,
    price NUMERIC(15,2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (store_document) REFERENCES stores (document)
);

CREATE UNIQUE INDEX CONCURRENTLY product_serie 
ON inventory (product_serie);
