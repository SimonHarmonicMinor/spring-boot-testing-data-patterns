CREATE TABLE post_tag
(
    tag_id  BIGINT NOT NULL,
    post_id BIGINT NOT NULL,
    PRIMARY KEY (tag_id, post_id)
);