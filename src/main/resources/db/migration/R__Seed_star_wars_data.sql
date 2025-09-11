-- Seed a default admin user
-- The password is a BCrypt hash for "admin123"
-- The user ID is a fixed UUID for consistency.
INSERT INTO tb_users (id, username, password) VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'admin', '$2a$10$3g4o8w.y.wZ2qVz9a8b7c.u3g4o8w.y.wZ2qVz9a8b7c.u3g4o8')
ON CONFLICT (id) DO UPDATE SET username = EXCLUDED.username, password = EXCLUDED.password;

-- Assign the ADMIN role to the new user
INSERT INTO tb_user_roles (user_id, role) VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'ROLE_ADMIN')
ON CONFLICT (user_id, role) DO NOTHING;