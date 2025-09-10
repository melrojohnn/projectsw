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

INSERT INTO tb_missions (id, public_id, title, description, status, rank) VALUES
(3, '10000000-0000-0000-0000-000000000003', 'The Kessel Run', 'Transport a volatile shipment of coaxium through the Kessel Run.', 'COMPLETED', 'EXPERT')
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, title = EXCLUDED.title, description = EXCLUDED.description, status = EXCLUDED.status, rank = EXCLUDED.rank;

INSERT INTO tb_missions (id, public_id, title, description, status, rank) VALUES
(4, '10000000-0000-0000-0000-000000000004', 'Battle of Hoth', 'Defend the Echo Base from the Imperial AT-AT assault.', 'FAILED', 'ADVANCED')
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, title = EXCLUDED.title, description = EXCLUDED.description, status = EXCLUDED.status, rank = EXCLUDED.rank;

INSERT INTO tb_missions (id, public_id, title, description, status, rank) VALUES
(5, '10000000-0000-0000-0000-000000000005', 'Find Master Yoda', 'Travel to the Dagobah system to find the exiled Jedi Master Yoda.', 'PENDING', 'INTERMEDIATE')
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, title = EXCLUDED.title, description = EXCLUDED.description, status = EXCLUDED.status, rank = EXCLUDED.rank;

INSERT INTO tb_missions (id, public_id, title, description, status, rank) VALUES
(6, '10000000-0000-0000-0000-000000000006', 'Infiltrate Jabba''s Palace', 'Rescue Han Solo from the clutches of Jabba the Hutt on Tatooine.', 'IN_PROGRESS', 'INTERMEDIATE')
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, title = EXCLUDED.title, description = EXCLUDED.description, status = EXCLUDED.status, rank = EXCLUDED.rank;

INSERT INTO tb_missions (id, public_id, title, description, status, rank) VALUES
(7, '10000000-0000-0000-0000-000000000007', 'Battle of Endor', 'Lead the Rebel fleet in an assault on the second Death Star.', 'PENDING', 'EXPERT')
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, title = EXCLUDED.title, description = EXCLUDED.description, status = EXCLUDED.status, rank = EXCLUDED.rank;

INSERT INTO tb_missions (id, public_id, title, description, status, rank) VALUES
(8, '10000000-0000-0000-0000-000000000008', 'Negotiate with the Gungans', 'Secure an alliance with the Gungan Grand Army on Naboo.', 'COMPLETED', 'EASY')
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, title = EXCLUDED.title, description = EXCLUDED.description, status = EXCLUDED.status, rank = EXCLUDED.rank;

INSERT INTO tb_missions (id, public_id, title, description, status, rank) VALUES
(9, '10000000-0000-0000-0000-000000000009', 'Scout the Outer Rim', 'Patrol the Outer Rim territories for signs of First Order activity.', 'IN_PROGRESS', 'BEGINNER')
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, title = EXCLUDED.title, description = EXCLUDED.description, status = EXCLUDED.status, rank = EXCLUDED.rank;

INSERT INTO tb_missions (id, public_id, title, description, status, rank) VALUES
(10, '10000000-0000-0000-0000-000000000010', 'Capture a Bounty Hunter', 'Track and capture the notorious bounty hunter Boba Fett.', 'PENDING', 'ADVANCED')
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, title = EXCLUDED.title, description = EXCLUDED.description, status = EXCLUDED.status, rank = EXCLUDED.rank;

INSERT INTO tb_missions (id, public_id, title, description, status, rank) VALUES
(11, '10000000-0000-0000-0000-000000000011', 'Sabotage the Droid Factory', 'Infiltrate Geonosis and sabotage the Separatist droid factory.', 'COMPLETED', 'INTERMEDIATE')
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, title = EXCLUDED.title, description = EXCLUDED.description, status = EXCLUDED.status, rank = EXCLUDED.rank;

INSERT INTO tb_missions (id, public_id, title, description, status, rank) VALUES
(12, '10000000-0000-0000-0000-000000000012', 'Deliver the R2 unit', 'Deliver the astromech droid containing vital information to the Rebel Alliance.', 'PENDING', 'BEGINNER')
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, title = EXCLUDED.title, description = EXCLUDED.description, status = EXCLUDED.status, rank = EXCLUDED.rank;


-- Seed Characters
-- Using ON CONFLICT to prevent errors and allow updates if the script runs again.
INSERT INTO tb_members (id, public_id, name, email, age, faction, rank, homeland, mission_id, image_url) VALUES
(1, '20000000-0000-0000-0000-000000000001', 'Luke Skywalker', 'luke@rebellion.org', 19, 'REBEL_ALLIANCE', 'COMMANDER', 'Tatooine', 2, 'https://static.wikia.nocookie.net/starwars/images/6/6c/LukeSkywalker-RotJAVA.png'),
(2, '20000000-0000-0000-0000-000000000002', 'Leia Organa', 'leia@rebellion.org', 19, 'REBEL_ALLIANCE', 'GENERAL', 'Alderaan', 2, 'https://static.wikia.nocookie.net/starwars/images/9/9b/Princessleiaheadwithgun.jpg'),
(3, '20000000-0000-0000-0000-000000000003', 'Jyn Erso', 'jyn@rebellion.org', 21, 'REBEL_ALLIANCE', 'SERGEANT', 'Vallt', 1, 'https://static.wikia.nocookie.net/starwars/images/b/b3/JynErsoPromo.png'),
(4, '20000000-0000-0000-0000-000000000004', 'Darth Vader', 'vader@empire.gov', 45, 'SITH', 'SITH_LORD', 'Tatooine', NULL, 'https://static.wikia.nocookie.net/starwars/images/9/94/Vaderrotj.jpg'),
(5, '20000000-0000-0000-0000-000000000005', 'R2-D2', 'r2d2@droids.bot', 66, 'DROID_ARMY', 'ASTROMECH_DROID', 'Naboo', 12, 'https://static.wikia.nocookie.net/starwars/images/e/eb/ArtooTFA2-Fathead.png'),
(6, '20000000-0000-0000-0000-000000000006', 'C-3PO', 'c3po@droids.bot', 66, 'DROID_ARMY', 'PROTOCOL_DROID', 'Tatooine', 2, 'https://static.wikia.nocookie.net/starwars/images/3/3f/C-3PO_TLJ_Card_Trader_Award_Card.png'),
(7, '20000000-0000-0000-0000-000000000007', 'Anakin Skywalker', 'anakin@jedicouncil.net', 22, 'SITH', 'SITH_LORD', 'Tatooine', 7, 'https://static.wikia.nocookie.net/starwars/images/6/6f/Anakin_Skywalker_RotS.png'),
(8, '20000000-0000-0000-0000-000000000008', 'Han Solo', 'han.solo@rebellion.org', 32, 'REBEL_ALLIANCE', 'CAPTAIN', 'Corellia', 6, 'https://static.wikia.nocookie.net/starwars/images/e/e2/TFAHanSolo.png'),
(9, '20000000-0000-0000-0000-000000000009', 'Kylo Ren', 'kylo@firstorder.io', 30, 'SITH', 'SITH_LORD', 'Chandrila', 9, 'https://static.wikia.nocookie.net/starwars/images/b/bc/KyloRenVFcover-TROS.png'),
(10, '20000000-0000-0000-0000-000000000010', 'Obi-Wan Kenobi', 'obiwan@jediorder.org', 57, 'JEDI_ORDER', 'MASTER', 'Stewjon', 5, 'https://static.wikia.nocookie.net/starwars/images/4/4e/ObiWanHS-SWE.jpg'),
(11, '20000000-0000-0000-0000-000000000011', 'Yoda', 'yoda@jediorder.org', 900, 'JEDI_ORDER', 'GRAND_MASTER', 'Unknown', 5, 'https://static.wikia.nocookie.net/starwars/images/d/d6/Yoda_SWSB.png'),
(12, '20000000-0000-0000-0000-000000000012', 'Cassian Andor', 'cassian@rebellion.org', 26, 'REBEL_ALLIANCE', 'CAPTAIN', 'Fest', 1, 'https://static.wikia.nocookie.net/starwars/images/3/31/CassianAndor-Fathead.png'),
(13, '20000000-0000-0000-0000-000000000013', 'Wilhuff Tarkin', 'tarkin@empire.gov', 64, 'GALACTIC_EMPIRE', 'GRAND_MOFF', 'Eriadu', 1, 'https://static.wikia.nocookie.net/starwars/images/c/c1/Tarkininfobox.jpg'),
(14, '20000000-0000-0000-0000-000000000014', 'Armitage Hux', 'hux@firstorder.io', 34, 'GALACTIC_EMPIRE', 'COMMANDER', 'Arkanis', 9, 'https://static.wikia.nocookie.net/starwars/images/d/d0/Hux.png'),
(15, '20000000-0000-0000-0000-000000000015', 'Orson Krennic', 'krennic@empire.gov', 51, 'GALACTIC_EMPIRE', 'COMMANDER', 'Lexrul', 1, 'https://static.wikia.nocookie.net/starwars/images/0/05/OrsonKrennic-SWI171.png'),
(16, '20000000-0000-0000-0000-000000000016', 'Captain Phasma', 'phasma@firstorder.io', 32, 'GALACTIC_EMPIRE', 'CAPTAIN', 'Parnassos', 9, 'https://static.wikia.nocookie.net/starwars/images/a/af/Phasma_TLJ_Unmasked_Art.png'),
(17, '20000000-0000-0000-0000-000000000017', 'Iden Versio', 'iden.versio@empire.gov', 24, 'GALACTIC_EMPIRE', 'COMMANDER', 'Vardos', 4, 'https://static.wikia.nocookie.net/starwars/images/7/79/Iden_Versio_BFII_render.png'),
(18, '20000000-0000-0000-0000-000000000018', 'Mace Windu', 'windu@jediorder.org', 53, 'JEDI_ORDER', 'MASTER', 'Haruun Kal', NULL, 'https://static.wikia.nocookie.net/starwars/images/f/fc/Mace_Windu.jpg'),
(19, '20000000-0000-0000-0000-000000000019', 'Qui-Gon Jinn', 'quigon@jediorder.org', 60, 'JEDI_ORDER', 'MASTER', 'Coruscant', 8, 'https://static.wikia.nocookie.net/starwars/images/f/f6/Qui-Gon_Jinn_Headshot_TPM.jpg'),
(20, '20000000-0000-0000-0000-000000000020', 'Ahsoka Tano', 'ahsoka@jediorder.org', 36, 'JEDI_ORDER', 'KNIGHT', 'Shili', NULL, 'https://static.wikia.nocookie.net/starwars/images/1/17/Ahsoka_Tano_TROS_databank.png'),
(21, '20000000-0000-0000-0000-000000000021', 'Padmé Amidala', 'padme@republic.gov', 27, 'REBEL_ALLIANCE', 'GENERAL', 'Naboo', 8, 'https://static.wikia.nocookie.net/starwars/images/b/b2/Padmegreenscrshot.jpg'),
(22, '20000000-0000-0000-0000-000000000022', 'Count Dooku', 'dooku@sith.org', 83, 'SITH', 'SITH_LORD', 'Serenno', 11, 'https://static.wikia.nocookie.net/starwars/images/b/b8/Dooku_Headshot.jpg'),
(23, '20000000-0000-0000-0000-000000000023', 'Asajj Ventress', 'ventress@sith.org', 28, 'SITH', 'ACOLYTE', 'Dathomir', NULL, 'https://static.wikia.nocookie.net/starwars/images/e/e3/AsajjVentress-TCD.png'),
(24, '20000000-0000-0000-0000-000000000024', 'Grand Admiral Thrawn', 'thrawn@empire.gov', 45, 'GALACTIC_EMPIRE', 'GRAND_MOFF', 'Csilla', NULL, 'https://static.wikia.nocookie.net/starwars/images/e/e3/Thrawn_Rebels.png'),
(25, '20000000-0000-0000-0000-000000000025', 'Boba Fett', 'boba.fett@bounty.net', 35, 'UNAFFILIATED', 'BOUNTY_HUNTER', 'Kamino', 10, 'https://static.wikia.nocookie.net/starwars/images/7/79/Boba_Fett_-_The_Empire_Strikes_Back.png'),
(26, '20000000-0000-0000-0000-000000000026', 'Din Djarin', 'mando@bounty.net', 38, 'UNAFFILIATED', 'MANDALORIAN', 'Aq Vetina', NULL, 'https://static.wikia.nocookie.net/starwars/images/6/6e/The_Mandalorian_BoBF_Updated_Armor.png'),
(27, '20000000-0000-0000-0000-000000000027', 'Lando Calrissian', 'lando@rebellion.org', 35, 'REBEL_ALLIANCE', 'GENERAL', 'Socorro', 7, 'https://static.wikia.nocookie.net/starwars/images/8/8f/Lando_ROTJ.png'),
(28, '20000000-0000-0000-0000-000000000028', 'Wedge Antilles', 'wedge@rebellion.org', 25, 'REBEL_ALLIANCE', 'COMMANDER', 'Corellia', 7, 'https://static.wikia.nocookie.net/starwars/images/6/60/WedgeHelmetless-ROTJ.png'),
(29, '20000000-0000-0000-0000-000000000029', 'Admiral Ackbar', 'ackbar@rebellion.org', 60, 'REBEL_ALLIANCE', 'GENERAL', 'Mon Cala', 7, 'https://static.wikia.nocookie.net/starwars/images/2/29/Ackbar_RH.png'),
(30, '20000000-0000-0000-0000-000000000030', 'Darth Maul', 'maul@sith.org', 30, 'SITH', 'SITH_LORD', 'Dathomir', NULL, 'https://static.wikia.nocookie.net/starwars/images/5/50/Darth_Maul_profile.png'),
(31, 'e64e9be2-24a9-4ad4-9602-92492d72978a', 'Rey Skywalker', 'rey@jedicouncil.net', 20, 'UNAFFILIATED', 'JEDI_ORDER_UNAFFILIATED', 'Jakku', NULL, 'https://static.wikia.nocookie.net/starwars/images/8/89/Rey_SWI168.png'),
(32, '2a028dd8-ff40-4783-8fc3-25cf3776ad76', 'Mon Mothma', 'mon@resistance.org', 41, 'REBEL_ALLIANCE', 'COMMANDER', 'Chandrila', NULL, 'https://static.wikia.nocookie.net/starwars/images/2/22/Mon_Mothma_Ahsoka_poster.png'),
(33, 'bbe4a31d-5c88-449d-b639-e43353eff74c', 'Darth Sidious (Emperor Palpatine)', 'emperor@empire.gov', 88, 'SITH', 'SITH_LORD', 'Naboo', NULL, 'https://static.wikia.nocookie.net/starwars/images/e/e2/Palpatine-CEUEEd.png'),
(34, '35caf665-cbc7-4e9a-b026-f67a219aa9ac', 'Din Grogu', 'grogu@resistance.org', 50, 'JEDI_ORDER', 'PADAWAN', 'Coruscant', NULL, 'https://static.wikia.nocookie.net/starwars/images/7/7c/AnnBembi-HelloFriend.png')
ON CONFLICT (id) DO UPDATE SET public_id = EXCLUDED.public_id, name = EXCLUDED.name, email = EXCLUDED.email, age = EXCLUDED.age, faction = EXCLUDED.faction, rank = EXCLUDED.rank, homeland = EXCLUDED.homeland, mission_id = EXCLUDED.mission_id, image_url = EXCLUDED.image_url;
