-- Migration to add the image_url column to the tb_members table

ALTER TABLE tb_members
ADD COLUMN image_url VARCHAR(1024);
