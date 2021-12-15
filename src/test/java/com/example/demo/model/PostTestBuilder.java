package com.example.demo.model;

import static java.util.stream.Collectors.toList;

import com.example.demo.test_util.Builder;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PostTestBuilder implements Builder<Post> {

  private String name = "post_name";
  private String content = "post_content";
  private double rating = 0;
  private List<Builder<Comment>> comments = new ArrayList<>();
  private Builder<User> author;
  private OffsetDateTime createdAt = OffsetDateTime.now();

  public static PostTestBuilder aPost() {
    return new PostTestBuilder();
  }

  private PostTestBuilder(PostTestBuilder builder) {
    this.name = builder.name;
    this.content = builder.content;
    this.rating = builder.rating;
    this.comments = new ArrayList<>(builder.comments);
    this.author = builder.author;
    this.createdAt = builder.createdAt;
  }

  private PostTestBuilder() {
  }

  public PostTestBuilder withName(String name) {
    final var copy = new PostTestBuilder(this);
    copy.name = name;
    return copy;
  }

  public PostTestBuilder withContent(String content) {
    final var copy = new PostTestBuilder(this);
    copy.content = content;
    return copy;
  }

  public PostTestBuilder withRating(double rating) {
    final var copy = new PostTestBuilder(this);
    copy.rating = rating;
    return copy;
  }

  public PostTestBuilder withComment(Builder<Comment> comment) {
    return withComments(List.of(comment));
  }

  public PostTestBuilder withComments(Collection<? extends Builder<Comment>> comments) {
    final var copy = new PostTestBuilder(this);
    copy.comments = new ArrayList<>(comments);
    return copy;
  }

  public PostTestBuilder withAuthor(Builder<User> author) {
    final var copy = new PostTestBuilder(this);
    copy.author = author;
    return copy;
  }

  public PostTestBuilder withCreatedAt(OffsetDateTime createdAt) {
    final var copy = new PostTestBuilder(this);
    copy.createdAt = createdAt;
    return copy;
  }

  @Override
  public Post build() {
    final var post = new Post();
    post.setName(name);
    post.setContent(content);
    post.setRating(rating);
    post.setComments(
        comments.stream()
            .map(Builder::build)
            .peek(c -> c.setPost(post))
            .collect(toList())
    );
    post.setAuthor(author.build());
    post.setCreatedAt(createdAt);
    return post;
  }
}