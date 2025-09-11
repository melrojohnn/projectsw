-- Migration to create user-related tables for authentication

-- Create the main users table
CREATE TABLE tb_users (
    id UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Create a table to store the roles for each user
CREATE TABLE tb_user_roles (
    user_id UUID NOT NULL,
    role VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, role),
    FOREIGN KEY (user_id) REFERENCES tb_users (id)
);
