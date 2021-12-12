package com.example.demo.model;

public class PostTestFactory {
  public static Post createPost(User author) {
    return createPost("name", "content", 0, author);
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
    return post;
  }
}