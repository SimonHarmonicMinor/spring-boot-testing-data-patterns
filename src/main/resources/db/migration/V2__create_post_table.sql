CREATE TABLE post
(
    post_id    BIGSERIAL PRIMARY KEY,
    name       TEXT                     NOT NULL,
    content    TEXT                     NOT NULL,
    author_id  BIGINT                   NOT NULL REFERENCES user (user_id),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);