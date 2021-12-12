CREATE TABLE post_tag
(
    tag_id  BIGINT NOT NULL REFERENCES tag (tag_id),
    post_id BIGINT NOT NULL REFERENCES post (post_id),
    PRIMARY KEY (tag_id, post_id)
);