-- V3__insert_initial_users.sql
-- Insert initial data into the users table

USE sca;

INSERT INTO users (id, name, email, password, role) VALUES
                                                        (UUID(), 'Maria Rodriguez', 'maria.rodriguez@example.com', '$2a$10$OXwZWNCGB.Wl3pux0LnWiOtWAqcIvCn3GABQoUrIdVBzau5K9Khou', 'admin'),
                                                        (UUID(), 'Juan Lopez', 'juan.lopez@example.com', '$2a$10$ydZPMnd9dKi006tP5CLgKuYmkmWWH328PRU3tQ4HMBaKzbq6W1vWu', 'user');