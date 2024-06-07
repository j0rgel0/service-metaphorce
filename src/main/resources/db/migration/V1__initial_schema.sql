-- Flyway script for creating the users table
-- This script creates a table named 'users' with the following columns:
-- - id: a CHAR(36) primary key, not null, with a default UUID value
-- - name: a VARCHAR(255) column for the user's name, not null
-- - email: a VARCHAR(255) column for the user's email, not null and unique

-- Create the 'sca' database if it doesn't exist
CREATE DATABASE IF NOT EXISTS sca;

-- Use the 'sca' database
USE sca;

-- Create the 'users' table if it doesn't exist
CREATE TABLE IF NOT EXISTS users (
    id CHAR(36) PRIMARY KEY NOT NULL DEFAULT (UUID()),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    UNIQUE (email)
);