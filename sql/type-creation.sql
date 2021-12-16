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

CREATE OR REPLACE FUNCTION products_test(IN store stores, IN producto products[])
RETURNS products[] AS $$
BEGIN
	RETURN producto;
END;
$$ LANGUAGE plpgsql;

SELECT products_test(
	(1,'Thomas Store','0001')::stores, 
	ARRAY[(1,'tv', (1, 1, 1, 20, 20.00)), (2,'PC', (1, 1, 1, 20, 20.00))]::products[]);