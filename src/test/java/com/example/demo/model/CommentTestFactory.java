package com.example.demo.model;

import java.time.OffsetDateTime;

public class CommentTestFactory {

  public static Comment createComment(User user, Post post) {
    return createComment(user, post, "");
  }

  public static Comment createComment(User user, Post post, String text) {
    final var comment = new Comment();
    comment.setAuthor(user);
    comment.setCreatedAt(OffsetDateTime.now());
    comment.setPost(post);
    comment.setText(text);
    return comment;
  }
}