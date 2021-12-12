package com.example.demo.model;

import static java.util.Optional.ofNullable;

import com.example.demo.test_util.Builder;
import java.time.OffsetDateTime;

public class CommentTestBuilder implements Builder<Comment> {

  private Builder<User> author;
  private String text = "comment_text";
  private OffsetDateTime createdAt = OffsetDateTime.now();
  private Builder<Post> post;

  public static CommentTestBuilder aComment() {
    return new CommentTestBuilder();
  }

  public CommentTestBuilder(CommentTestBuilder builder) {
    this.author = builder.author;
    this.text = builder.text;
    this.createdAt = builder.createdAt;
    this.post = builder.post;
  }

  private CommentTestBuilder() {
  }

  public CommentTestBuilder withAuthor(Builder<User> author) {
    final var copy = new CommentTestBuilder(this);
    copy.author = author;
    return copy;
  }

  public CommentTestBuilder withText(String text) {
    final var copy = new CommentTestBuilder(this);
    copy.text = text;
    return copy;
  }

  public CommentTestBuilder withCreatedAt(OffsetDateTime createdAt) {
    final var copy = new CommentTestBuilder(this);
    copy.createdAt = createdAt;
    return copy;
  }

  public CommentTestBuilder withPost(Builder<Post> post) {
    final var copy = new CommentTestBuilder(this);
    copy.post = post;
    return copy;
  }

  @Override
  public Comment build() {
    final var comment = new Comment();
    comment.setText(text);
    comment.setAuthor(
        ofNullable(author)
            .map(Builder::build)
            .orElse(null)
    );
    comment.setCreatedAt(createdAt);
    comment.setPost(
        ofNullable(post)
            .map(Builder::build)
            .orElse(null)
    );
    return comment;
  }
}
