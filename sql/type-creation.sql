CREATE TYPE stores AS (
	id INTEGER,
	nome VARCHAR(255),
	documento VARCHAR(255)
);

CREATE TYPE inventories AS (
    id INTEGER,
    product_id INTEGER,
    store_id INTEGER,
    amount INTEGER,
    price NUMERIC(15,2)
);

CREATE TYPE products AS (
	id INTEGER,
	nome VARCHAR(255),
	inventory INVENTORIES
);

CREATE OR REPLACE FUNCTION products_test(store IN stores, producto IN products[])
RETURNS products[] AS $$
BEGIN
	RETURN producto;
END;
$$ LANGUAGE plpgsql;

SELECT products_test(ARRAY[
	'(1,"tv", {1, 1, 1, 20, 20.00}::inventories)',
	'(2,"PC", INVENTORIES(1, 1, 1, 20, 20.00))'
]::products[]);

SELECT ARRAY[
	'(1,"tv", inventories(1, 1, 1, 20, 20.00))',
	'(2,"PC", INVENTORIES(1, 1, 1, 20, 20.00))'
]::products[];

SELECT c.product_id, c.store_id, c.price FROM inventories c;

SELECT p FROM products p;

SELECT s FROM stores s;

DROP FUNCTION products_test(products[]);
DROP TYPE products CASCADE;