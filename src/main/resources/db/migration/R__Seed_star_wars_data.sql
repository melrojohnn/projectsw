-- Repeatable migration for seeding initial Star Wars data.
-- This script will run every time its content changes.

-- Seed Missions
-- Using ON CONFLICT to prevent errors and allow updates if the script runs again.
INSERT INTO tb_missions (id, public_id, title, description, status, rank) VALUES
(1, '10000000-0000-0000-0000-000000000001', 'Steal Death Star Plans', 'Infiltrate the Empire base on Scarif to steal the plans for the Death Star.', 'IN_PROGRESS', 'ADVANCED')
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, title = EXCLUDED.title, description = EXCLUDED.description, status = EXCLUDED.status, rank = EXCLUDED.rank;

INSERT INTO tb_missions (id, public_id, title, description, status, rank) VALUES
(2, '10000000-0000-0000-0000-000000000002', 'Rescue Princess Leia', 'Rescue Princess Leia from the Death Star detention block AA-23.', 'IN_PROGRESS', 'INTERMEDIATE')
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, title = EXCLUDED.title, description = EXCLUDED.description, status = EXCLUDED.status, rank = EXCLUDED.rank;

-- Seed Characters
-- Using ON CONFLICT to prevent errors and allow updates if the script runs again.
INSERT INTO tb_members (id, public_id, name, email, age, faction, rank, homeland, mission_id) VALUES
(1, '20000000-0000-0000-0000-000000000001', 'Luke Skywalker', 'luke@rebellion.org', 19, 'REBEL_ALLIANCE', 'COMMANDER', 'Tatooine', 2)
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, name = EXCLUDED.name, email = EXCLUDED.email, age = EXCLUDED.age, faction = EXCLUDED.faction, rank = EXCLUDED.rank, homeland = EXCLUDED.homeland, mission_id = EXCLUDED.mission_id;

INSERT INTO tb_members (id, public_id, name, email, age, faction, rank, homeland, mission_id) VALUES
(2, '20000000-0000-0000-0000-000000000002', 'Leia Organa', 'leia@rebellion.org', 19, 'REBEL_ALLIANCE', 'GENERAL', 'Alderaan', 2)
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, name = EXCLUDED.name, email = EXCLUDED.email, age = EXCLUDED.age, faction = EXCLUDED.faction, rank = EXCLUDED.rank, homeland = EXCLUDED.homeland, mission_id = EXCLUDED.mission_id;

INSERT INTO tb_members (id, public_id, name, email, age, faction, rank, homeland, mission_id) VALUES
(3, '20000000-0000-0000-0000-000000000003', 'Jyn Erso', 'jyn@rebellion.org', 21, 'REBEL_ALLIANCE', 'SERGEANT', 'Vallt', 1)
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, name = EXCLUDED.name, email = EXCLUDED.email, age = EXCLUDED.age, faction = EXCLUDED.faction, rank = EXCLUDED.rank, homeland = EXCLUDED.homeland, mission_id = EXCLUDED.mission_id;

INSERT INTO tb_members (id, public_id, name, email, age, faction, rank, homeland, mission_id) VALUES
(4, '20000000-0000-0000-0000-000000000004', 'Darth Vader', 'vader@empire.gov', 45, 'SITH', 'SITH_LORD', 'Tatooine', NULL)
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, name = EXCLUDED.name, email = EXCLUDED.email, age = EXCLUDED.age, faction = EXCLUDED.faction, rank = EXCLUDED.rank, homeland = EXCLUDED.homeland, mission_id = EXCLUDED.mission_id;
