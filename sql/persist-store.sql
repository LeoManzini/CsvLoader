CREATE OR REPLACE FUNCTION persistStore()
RETURNS INTEGER AS $$
DECLARE
	database_store INTEGER := 0;
BEGIN
	SELECT * INTO database_store FROM store WHERE id = 1;
	
	IF NOT FOUND 
	THEN
		RETURN 5;
	ELSE
		RETURN database_store;
	END IF;
END;
$$ LANGUAGE plpgsql;

SELECT persistStore();