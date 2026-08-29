-- V2__add_auth_fields_to_users.sql

ALTER TABLE users
    ADD COLUMN password VARCHAR(255) NOT NULL,
    ADD COLUMN provider_id VARCHAR(255),
    ADD COLUMN auth_provider_type VARCHAR(50) ;
ALTER TABLE users
    ADD CONSTRAINT UK_USERS_PASSWORD UNIQUE (password);
CREATE INDEX idx_provider_id_provider_type ON users (provider_id, auth_provider_type);
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            roles   VARCHAR(50) NOT NULL,
                            CONSTRAINT PK_USER_ROLES PRIMARY KEY (user_id, roles),
                            CONSTRAINT FK_USER_ROLES_USER FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);