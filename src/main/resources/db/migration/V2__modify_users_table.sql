-- Flyway script for modifying the users table to add new columns, if the table exists
-- This script adds the following columns to the 'users' table:
-- - password: a VARCHAR(100) column for the user's password, not null
-- - creation_date: a TIMESTAMP column for the creation date, not null, with a default value of the current timestamp
-- - last_update: a TIMESTAMP column for the last update date, with a default value of the current timestamp
-- - soft_delete: a BOOLEAN column for soft delete status, not null, with a default value of false
-- - role: a VARCHAR(15) column for the user's role, not null

-- V2__modify_users_table.sql
ALTER TABLE users ADD COLUMN password VARCHAR(100) NOT NULL;
ALTER TABLE users ADD COLUMN creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE users ADD COLUMN last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
ALTER TABLE users ADD COLUMN soft_delete BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE users ADD COLUMN role VARCHAR(15) NOT NULL;