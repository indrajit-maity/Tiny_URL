CREATE TABLE password_reset_tokens (
  id BIGINT NOT NULL AUTO_INCREMENT,
  token VARCHAR(255) NOT NULL UNIQUE,
 otp VARCHAR(255) NOT NULL,
 user_id BIGINT NOT NULL,
 expiry_date DATETIME NOT NULL,
 used BOOLEAN NOT NULL,
 attempt_count INT NOT NULL,
created_at DATETIME,

 PRIMARY KEY (id),

 CONSTRAINT fk_password_reset_user
 FOREIGN KEY (user_id)
 REFERENCES users(id)
);