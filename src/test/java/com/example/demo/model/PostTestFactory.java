package com.example.demo.model;

import java.time.OffsetDateTime;

public class PostTestFactory {
  public static Post createPost(User author) {
    return createPost("name", "content", 0, author);
  }

  public static Post createPost(double rating, User author) {
    return createPost("name", "content", rating, author);
  }

  public static Post createPost(String name, User author) {
    return createPost(name, "content", 0, author);
  }

  public static Post createPost(String name, String content, User author) {
    return createPost(name, content, 0, author);
  }

  public static Post createPost(String name, String content, double rating, User author) {
    final var post = new Post();
    post.setName(name);
    post.setContent(content);
    post.setRating(rating);
    post.setAuthor(author);
    post.setCreatedAt(OffsetDateTime.now());
    return post;
  }
}