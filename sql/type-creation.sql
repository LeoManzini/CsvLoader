CREATE TYPE products AS (
	id INTEGER,
	nome VARCHAR(255)
);

CREATE OR REPLACE FUNCTION products_test(producto IN products[])
RETURNS products[] AS $$
BEGIN
	RETURN producto;
END;
$$ LANGUAGE plpgsql;

SELECT products_test(ARRAY[
	'(1,"tv")',
	'(2,"PC")'
]::products[]);

DROP FUNCTION products_test(products[]);
DROP TYPE products CASCADE;