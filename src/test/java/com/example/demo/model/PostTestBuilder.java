package com.example.demo.model;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

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
  private List<Builder<Tag>> tags = new ArrayList<>();

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
    this.tags = new ArrayList<>(builder.tags);
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

  public PostTestBuilder withTag(Builder<Tag> tag) {
    return withTags(List.of(tag));
  }

  public PostTestBuilder withTags(Collection<? extends Builder<Tag>> tags) {
    final var copy = new PostTestBuilder(this);
    copy.tags = new ArrayList<>(tags);
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
            .collect(toList())
    );
    post.setAuthor(author.build());
    post.setCreatedAt(createdAt);
    post.setTags(
        tags.stream()
            .map(Builder::build)
            .collect(toSet())
    );
    return post;
  }
}