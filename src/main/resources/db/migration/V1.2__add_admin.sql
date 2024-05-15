INSERT INTO users (id, username, role_name, password, email)
VALUES (1, 'admin', 'ADMIN', '$2a$10$5QcvErG7h0sYOAFpm//Wg.2qMzPgyj2DOF6VA6RYtP5IYgD8ygPXe', 'admin@example.com');

ALTER SEQUENCE users_id_seq RESTART WITH 2;