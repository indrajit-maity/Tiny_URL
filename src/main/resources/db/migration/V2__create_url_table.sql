CREATE TABLE url (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        original_url VARCHAR(2048) NOT NULL,
        short_code VARCHAR(255) UNIQUE,
        short_url VARCHAR(255) UNIQUE,
        click_count INT NOT NULL DEFAULT 0,
        created_at DATE,
        expiry_date DATE,
        is_active BOOLEAN NOT NULL DEFAULT TRUE,
        user_id BIGINT,
        CONSTRAINT fk_url_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);