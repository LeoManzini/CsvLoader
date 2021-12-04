CREATE TABLE IF NOT EXISTS store (
	id INTEGER NOT NULL,
	nome VARCHAR(255) NOT NULL,
	document VARCHAR(80) NOT NULL,
	store_product INTEGER NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (store_product) REFERENCES product (id)
);

CREATE TABLE IF NOT EXISTS product (
    id INTEGER NOT NULL,
    nome VARCHAR(255) NOT NULL,
    price NUMERIC(15,2) NOT NULL,
    store_id INTEGER NOT NULL,
    inventory_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (store_id) REFERENCES store (id),
    FOREIGN KEY (inventory_id) REFERENCES inventory (id)
);

CREATE TABLE IF NOT EXISTS inventory (
    id INTEGER NOT NULL PRIMARY KEY,
    amount INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product (id)
);