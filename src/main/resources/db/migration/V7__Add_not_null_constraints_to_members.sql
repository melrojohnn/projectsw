-- Add NOT NULL constraints to the tb_members table to enforce data integrity

-- Note: These operations will fail if any existing rows have null values in these columns.
-- Our seed script already provides values, so this should be safe.

ALTER TABLE tb_members ALTER COLUMN name SET NOT NULL;
ALTER TABLE tb_members ALTER COLUMN email SET NOT NULL;
ALTER TABLE tb_members ALTER COLUMN faction SET NOT NULL;
ALTER TABLE tb_members ALTER COLUMN rank SET NOT NULL;
