-- Add NOT NULL constraints to the tb_missions table to enforce data integrity

-- Note: These operations will fail if any existing rows have null values in these columns.
-- Our seed script already provides values, so this should be safe.

ALTER TABLE tb_missions ALTER COLUMN title SET NOT NULL;
ALTER TABLE tb_missions ALTER COLUMN status SET NOT NULL;
ALTER TABLE tb_missions ALTER COLUMN rank SET NOT NULL;
