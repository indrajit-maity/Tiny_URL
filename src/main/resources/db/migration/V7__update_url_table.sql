ALTER TABLE url
    ADD COLUMN updated_at DATETIME,
    ADD COLUMN user_id BIGINT,
    ADD COLUMN version BIGINT NOT NULL DEFAULT 0;

ALTER TABLE url
    ADD CONSTRAINT fk_urls_user
    FOREIGN KEY (user_id)
    REFERENCES users(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

CREATE INDEX idx_short_code
    ON url(short_code);

CREATE INDEX idx_original_url
    ON url(original_url(255));

CREATE INDEX idx_user_id
    ON url(user_id);