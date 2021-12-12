package com.example.demo.repository.query;

import java.time.OffsetDateTime;

public record CommentView(
    Long id,
    String text,
    OffsetDateTime createdAt
) {

}
