CREATE TABLE user
(
    user_id    BIGSERIAL PRIMARY KEY,
    login      TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name  TEXT NOT NULL
);