-- Migration to add the homeland column to the tb_members table

ALTER TABLE tb_members
ADD COLUMN homeland VARCHAR(255);
