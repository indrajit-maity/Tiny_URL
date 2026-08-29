ALTER TABLE users
    ADD COLUMN account_non_locked BOOLEAN NOT NULL DEFAULT TRUE,
    ADD COLUMN account_non_expired BOOLEAN NOT NULL DEFAULT TRUE,
    ADD COLUMN credentials_non_expired BOOLEAN NOT NULL DEFAULT TRUE,
    ADD COLUMN failed_attempts INT NOT NULL DEFAULT 0,
    ADD COLUMN last_password_reset_date DATETIME,
    ADD COLUMN created_date DATETIME NOT NULL,
    ADD COLUMN updated_date DATETIME,
    ADD COLUMN locked_at DATETIME;