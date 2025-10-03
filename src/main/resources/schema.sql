CREATE TABLE users (
    id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE roles (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
    user_id VARCHAR(50),
    role_id VARCHAR(50),
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE permissions (
    id SERIAL PRIMARY KEY,
    role_id VARCHAR(50),
    action VARCHAR(50) NOT NULL,
    resource VARCHAR(100) NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Sample data
INSERT INTO users (id, username, password) VALUES
('user1', 'admin', '$2a$10$exampleHashedPassword1'), -- Use BCrypt for real passwords
('user2', 'editor', '$2a$10$exampleHashedPassword2'),
('user3', 'viewer', '$2a$10$exampleHashedPassword3');

INSERT INTO roles (id, name) VALUES
('role1', 'ADMIN'),
('role2', 'EDITOR'),
('role3', 'VIEWER');

INSERT INTO user_roles (user_id, role_id) VALUES
('user1', 'role1'),
('user2', 'role2'),
('user2', 'role3'),
('user3', 'role3');

INSERT INTO permissions (role_id, action, resource) VALUES
('role1', 'READ', 'DOCUMENT'),
('role1', 'WRITE', 'DOCUMENT'),
('role1', 'DELETE', 'DOCUMENT'),
('role2', 'READ', 'DOCUMENT'),
('role2', 'WRITE', 'DOCUMENT'),
('role3', 'READ', 'DOCUMENT');



CREATE TABLE users (
    id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    avatar VARCHAR(255)
);

CREATE TABLE roles (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
    user_id VARCHAR(50),
    role_id VARCHAR(50),
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE permissions (
    id SERIAL PRIMARY KEY,
    role_id VARCHAR(50),
    action VARCHAR(50) NOT NULL,
    resource VARCHAR(100) NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Sample data
INSERT INTO users (id, username, password, email, avatar) VALUES
('user1', 'admin', '$2a$10$exampleHashedPassword1', 'admin@example.com', 'https://example.com/avatars/admin.png'),
('user2', 'editor', '$2a$10$exampleHashedPassword2', 'editor@example.com', 'https://example.com/avatars/editor.png'),
('user3', 'viewer', '$2a$10$exampleHashedPassword3', 'viewer@example.com', 'https://example.com/avatars/viewer.png');

INSERT INTO roles (id, name) VALUES
('role1', 'ADMIN'),
('role2', 'EDITOR'),
('role3', 'VIEWER');

INSERT INTO user_roles (user_id, role_id) VALUES
('user1', 'role1'),
('user2', 'role2'),
('user2', 'role3'),
('user3', 'role3');

INSERT INTO permissions (role_id, action, resource) VALUES
('role1', 'READ', 'DOCUMENT'),
('role1', 'WRITE', 'DOCUMENT'),
('role1', 'DELETE', 'DOCUMENT'),
('role2', 'READ', 'DOCUMENT'),
('role2', 'WRITE', 'DOCUMENT'),
('role3', 'READ', 'DOCUMENT'),
-- Add permissions for user management (for ADMIN)
('role1', 'CREATE', 'USER'),
('role1', 'READ', 'USER'),
('role1', 'UPDATE', 'USER'),
('role1', 'DELETE', 'USER'),
('role1', 'CREATE', 'ROLE'),
('role1', 'READ', 'ROLE'),
('role1', 'UPDATE', 'ROLE'),
('role1', 'DELETE', 'ROLE'),
('role1', 'CREATE', 'PERMISSION'),
('role1', 'READ', 'PERMISSION'),
('role1', 'UPDATE', 'PERMISSION'),
('role1', 'DELETE', 'PERMISSION');