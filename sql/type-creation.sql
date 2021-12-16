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

DROP FUNCTION products_test(stores,products[])

CREATE OR REPLACE FUNCTION products_test(IN var_store_id INTEGER, IN var_producto products[])
RETURNS INTEGER AS $$
BEGIN
	SELECT * FROM store WHERE id = var_store_id;

	IF NOT FOUND 
	THEN
		RETURN 1;
	ELSE
		RETURN 2;
	END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION products_test(IN var_store stores, IN var_producto products[])
RETURNS integer AS '
    SELECT 1 FROM store WHERE id = var_store.id;
 '
LANGUAGE SQL;

SELECT products_test(
	(1), 
	ARRAY[(1,'Wristwatch', (null,1,2,20,90.00))]::products[]);

SELECT products_test(
	(1,'Thomas Store','0001')::stores, 
	ARRAY[(1,'Wristwatch', (null,1,2,20,90.00)), (2,'Smarth Watch', (null,2,2,8,150.00))]::products[]);