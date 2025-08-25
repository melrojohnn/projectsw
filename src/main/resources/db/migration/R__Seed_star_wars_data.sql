-- Repeatable migration for seeding initial Star Wars data.
-- This script will run every time its content changes.

-- Seed Missions
-- Using ON CONFLICT to prevent errors if the script runs again with the same data.
INSERT INTO tb_missions (id, title, description, status, rank) VALUES
(1, 'Steal Death Star Plans', 'Infiltrate the Empire base on Scarif to steal the plans for the Death Star.', 'ACTIVE', 'General')
ON CONFLICT (id) DO NOTHING;

INSERT INTO tb_missions (id, title, description, status, rank) VALUES
(2, 'Rescue Princess Leia', 'Rescue Princess Leia from the Death Star detention block AA-23.', 'ACTIVE', 'Commander')
ON CONFLICT (id) DO NOTHING;

-- Seed Characters
-- Using ON CONFLICT on the unique email column.
INSERT INTO tb_members (name, email, age, rank, homeland, mission_id) VALUES
('Luke Skywalker', 'luke@rebellion.org', 19, 'Commander', 'Tatooine', 2)
ON CONFLICT (email) DO NOTHING;

INSERT INTO tb_members (name, email, age, rank, homeland, mission_id) VALUES
('Leia Organa', 'leia@rebellion.org', 19, 'General', 'Alderaan', 2)
ON CONFLICT (email) DO NOTHING;

INSERT INTO tb_members (name, email, age, rank, homeland, mission_id) VALUES
('Jyn Erso', 'jyn@rebellion.org', 21, 'Sergeant', 'Vallt', 1)
ON CONFLICT (email) DO NOTHING;

INSERT INTO tb_members (name, email, age, rank, homeland, mission_id) VALUES
('Darth Vader', 'vader@empire.gov', 45, 'Sith Lord', 'Tatooine', NULL)
ON CONFLICT (email) DO NOTHING;
