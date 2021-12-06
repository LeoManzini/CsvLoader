DROP TABLE STORE CASCADE;
DROP TABLE PRODUCT CASCADE;
DROP TABLE INVENTORY CASCADE;

CREATE TABLE IF NOT EXISTS store (
	id INTEGER NOT NULL,
	nome VARCHAR(255) NOT NULL,
	document VARCHAR(80) NOT NULL,
	store_product INTEGER NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product (
    id INTEGER NOT NULL,
    nome VARCHAR(255) NOT NULL,
    price NUMERIC(15,2) NOT NULL,
    store_id INTEGER NOT NULL,
    inventory_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (store_id) REFERENCES store (id)
);

CREATE TABLE IF NOT EXISTS inventory (
    id INTEGER NOT NULL PRIMARY KEY,
    amount INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product (id)
);

ALTER TABLE store
ADD Constraint store_product_id_fkey
FOREIGN KEY (store_product) REFERENCES product (id);

ALTER TABLE product
ADD Constraint product_inventory_id_fkey
FOREIGN KEY (inventory_id) REFERENCES inventory (id);