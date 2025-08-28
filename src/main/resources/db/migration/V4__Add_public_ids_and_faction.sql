-- Enable the pgcrypto extension to use gen_random_uuid()
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Migration to add public-facing UUIDs and the faction column

-- Add public_id to missions table
ALTER TABLE tb_missions
ADD COLUMN public_id UUID;

-- Add public_id and faction to members table
ALTER TABLE tb_members
ADD COLUMN public_id UUID,
ADD COLUMN faction VARCHAR(255);

-- Backfill existing rows with UUIDs to ensure the non-null constraint can be added later if needed
UPDATE tb_missions SET public_id = gen_random_uuid() WHERE public_id IS NULL;
UPDATE tb_members SET public_id = gen_random_uuid() WHERE public_id IS NULL;
