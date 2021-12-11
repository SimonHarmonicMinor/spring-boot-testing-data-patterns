CREATE TABLE comment
(
    comment_id BIGSERIAL PRIMARY KEY,
    text       TEXT                     NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    author_id  BIGINT                   NOT NULL REFERENCES user (user_id)
);